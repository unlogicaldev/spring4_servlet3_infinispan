package com.spring4.servlet3.sample.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class GlobalCacheUtil {

	private static CacheManager  cacheManager;
	
	@Autowired
	public GlobalCacheUtil(CacheManager c){
		try {
			cacheManager = c;			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getGlobalCache(String cacheName, String key) throws Exception{
		try{
			return cacheManager.getCache(cacheName).get(key).get();
		}catch(Exception e){
			return null;
		}
	}
	
	public static void setGlobalCache(String cacheName, String key, Object o) throws Exception{
		cacheManager.getCache(cacheName).put(key, o);
	}
	
	public static void evitGlobalCache(String cacheName, String key) throws Exception{
		cacheManager.getCache(cacheName).evict(key);
	}
}
