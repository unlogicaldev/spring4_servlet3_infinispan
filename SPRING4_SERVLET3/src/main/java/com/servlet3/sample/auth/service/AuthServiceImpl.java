package com.servlet3.sample.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.servlet3.sample.common.abs.AbstractBaseService;
import com.servlet3.sample.common.util.MD5Generator;

//@Service("authService")
public class AuthServiceImpl extends AbstractBaseService<AuthServiceImpl>
		implements AuthService {
	
	@Autowired CacheManager cacheManager;

	@Override
	public void createAuthInfo(DBObject auth) throws Exception {
		auth.put("status", 0);
		auth.put("regDate", new Date().getTime());
		getDBCollection("AuthLog").insert(auth);
	}

	@Override
//	@Cacheable(value = "authCache", key = "#key")
	public DBObject getAuthInfo(String key) throws Exception {
		DBObject r = null;
		ValueWrapper auth = cacheManager.getCache("authCache").get(key);
		if(auth != null){
			r = (DBObject) auth.get();
		}else{
			DBCursor cr = getDBCollection("AuthLog").find(new BasicDBObject("key",key)).limit(1).hint("AuthLog_idx_01");
			if(cr.hasNext()){
				r = cr.next();
				authInfoExpireProc(r);
				cacheManager.getCache("authCache").put(key,r);
				return r;
			}else{
				throw new Exception("400"); // 키가 없음
			}
		}
		
		authInfoExpireProc(r);
		return r;
		
	}

	private void authInfoExpireProc(DBObject r) throws Exception {
		int status = (Integer) r.get("status");
		long regDate = (Long) r.get("regDate");
		if(status == 1){
			long now = new Date().getTime();
			if((now - regDate) > 1000){
				throw new Exception("402"); // 키 유효기간 만료
			}
		}else{
			throw new Exception("401"); // 키는 있는데 사용 불가함
		}
	}

	@Override
	public void removeAuthInfo(String key) throws Exception {
		DBCursor cr = getDBCollection("AuthLog").find(new BasicDBObject("key",key)).limit(1).hint("AuthLog_idx_01");
		if(cr.hasNext()){
			DBObject o = cr.next();
			o.removeField("_id");
			o.put("status", 9);
			o.put("regDate", new Date().getTime());
			getDBCollection("AuthLog").insert(o);
			cacheManager.getCache("authCache").evict(key);
		}else{
			throw new Exception("400"); // 키가 없음
		}
	}

	@Override
	@CachePut(value = "authCache", key = "#key")
	public DBObject generateAuthInfo(String key) throws Exception {
		DBCursor cr = getDBCollection("AuthLog").find(new BasicDBObject("key",key)).limit(1).hint("AuthLog_idx_01");
		if(cr.hasNext()){
			DBObject o = cr.next();
			int status = (Integer) o.get("status");
			if(status == 0){
				o.removeField("_id");
				o.put("status", 1);
				o.put("regDate", new Date().getTime());
				getDBCollection("AuthLog").insert(o);
				return o;
			}else if(status == 3){
				o.removeField("_id");
				o.put("status", 1);
				o.put("regDate", new Date().getTime());
				getDBCollection("AuthLog").insert(o);
				return o;
			}else {
				throw new Exception("401"); // 키는 있는데 사용 불가함
			}
		}else{
			throw new Exception("400"); // 키가 없음
		}
	}

	@Override
	public DBObject reGenerateAuthInfo(String key, String newKey) throws Exception {
		DBCursor cr = getDBCollection("AuthLog").find(new BasicDBObject("key",key)).limit(1).hint("AuthLog_idx_01");
		if(cr.hasNext()){
			DBObject o = cr.next();
			o.removeField("_id");
			o.put("status", 3);
			o.put("regDate", new Date().getTime());
			o.put("key", newKey);
			o.put("oldKey", key);
			getDBCollection("AuthLog").insert(o);
			cacheManager.getCache("authCache").evict(key);
			return o;
		}else{
			throw new Exception("400"); // 키가 없음
		}
	}

}
