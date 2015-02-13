package com.servlet3.sample.web.test.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.servlet3.sample.common.abs.AbstractBaseService;

@Service("testService")
public class TestServiceImpl extends AbstractBaseService<TestServiceImpl>
		implements TestService {

	@SuppressWarnings("unchecked")
	@Override
	public List<DBObject> selectTestList(DBObject o) throws Exception {
		return commonSql.selectList("Test.selectTestList", o);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DBObject> selectTestListPost(DBObject o) throws Exception {
		return commonSqlPost.selectList("Test.selectTestListPost", o);
	}
	
	@Override
	@Cacheable(value = "default", key = "#id")
	public DBObject selectTest(String id) throws Exception {
		return (DBObject) commonSql.selectOne("Test.selectTest", id);
	}

	@Override
	public void insertTest(DBObject o) throws Exception {
		commonSql.insert("Test.insertTest", o);
	}

	@Override
	public void updateTeset(DBObject o) throws Exception {
		commonSql.update("Test.updateTest", o);
	}

}
