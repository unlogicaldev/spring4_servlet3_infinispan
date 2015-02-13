package com.servlet3.sample.common.mongo;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.servlet3.sample.BaseTest;
import com.servlet3.sample.common.util.DateUtil;

public class MongoTest_03_Geo extends BaseTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {

		DBObject o1 = new BasicDBObject("title", "남산")
								.append("loc", Arrays.asList(55,34.7))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o2 = new BasicDBObject("title", "북산")
								.append("loc", Arrays.asList(50,20.7))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o3 = new BasicDBObject("title", "서산")
								.append("loc", Arrays.asList(34.6,36.7))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o4 = new BasicDBObject("title", "동산")
								.append("loc", Arrays.asList(21,44))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o5 = new BasicDBObject("title", "중")
								.append("loc", Arrays.asList(40,40))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));
		

		DBCollection c = MongoTemplate.getDBCollection("TEST_INDEX_GEO");
		c.drop(); //테스트를 위한 초기화
		
		c.insert(Arrays.asList(o1,o2,o3,o4,o5));
		

		DBObject idx1 = new BasicDBObject("loc","2d");
		c.createIndex(idx1,"idx_TEST_GEO_01");
		
		BasicDBObject com = new BasicDBObject(); 
		com.append("geoNear", "TEST_INDEX_GEO"); 
		double[] loc = {50,30}; 
		com.append("near", loc); 
		com.append("num", 5); 
		//com.append("spherical", true); 
		com.append("maxDistance", 10 ); 
		System.out.println(com); 
		CommandResult myResults = MongoTemplate.getDB().command(com); 
		System.out.println(myResults);
		List<DBObject> arr = (List<DBObject>) myResults.get("results");
		for(DBObject o : arr){
			System.out.println(o);
		}
		
		fail("Not yet implemented");
	}

}
