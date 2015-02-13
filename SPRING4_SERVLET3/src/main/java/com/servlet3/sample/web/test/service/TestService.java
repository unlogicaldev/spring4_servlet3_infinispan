package com.servlet3.sample.web.test.service;

import java.util.List;

import com.mongodb.DBObject;

public interface TestService {

	List<DBObject> selectTestList(DBObject o) throws Exception;
	
	DBObject selectTest(String id) throws Exception;
	
	void insertTest(DBObject o) throws Exception;
	
	void updateTeset(DBObject o) throws Exception;
	
	List<DBObject> selectTestListPost(DBObject o) throws Exception;
	
}
