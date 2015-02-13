package com.spring4.servlet3.sample.common.mongo;


import java.util.Arrays;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.spring4.servlet3.sample.BaseTest;
import com.spring4.servlet3.sample.common.mongo.MongoTemplate;
import com.spring4.servlet3.sample.common.util.DateUtil;

public class MongoTest_02_Index extends BaseTest {

	@Test
	public void test() {

		DBObject o1 = new BasicDBObject("name", "Rhys Lee")
								.append("age", 29)
								.append("sex", 1)
								.append("type", 6)
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o2 = new BasicDBObject("name", "Ari")
								.append("age", 23)
								.append("sex", 0)
								.append("type", 3)
								.append("profile", new BasicDBObject("age",34))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o3= new BasicDBObject("name", "Ari2")
								.append("age", 23)
								.append("sex", 0)
								.append("type", 3)
								.append("profile", new BasicDBObject("age",22))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o4 = new BasicDBObject("name", "Ari3")
								.append("age", 23)
								.append("sex", 0)
								.append("type", 2)
								.append("profile", new BasicDBObject("age",21))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o5 = new BasicDBObject("name", "Ari4")
								.append("age", 26)
								.append("sex", 0)
								.append("type", 3)
								.append("profile", new BasicDBObject("age",45))
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));
		
		DBCollection c = MongoTemplate.getDBCollection("TEST_INDEX");
		c.drop(); //테스트를 위한 초기화
		
		c.insert(Arrays.asList(o1,o2,o3,o4,o5));
		
		DBObject idx1 = new BasicDBObject("name",1).append("profile.age", 1);
		c.createIndex(idx1,"idx_TEST_01",true);

		System.out.println(c.find(new BasicDBObject("name",new BasicDBObject("$ne", null))).explain());
		System.out.println(c.find(new BasicDBObject("name",new BasicDBObject("$ne", null))).hint("idx_TEST_01").explain());
		System.out.println(c.find(new BasicDBObject("name",new BasicDBObject("$ne", null).append("profile.age", new BasicDBObject("$gt",22)))).explain());
		
		DBCursor cr = c.find(new BasicDBObject("name",new BasicDBObject("$ne", null)).append("profile.age",new BasicDBObject("$gt",22)));
		while(cr.hasNext()){
			System.out.println(cr.next());
		}
		
		for(DBObject o : c.getIndexInfo()){
			System.out.println(o);
		}
		
		fail("Not yet implemented");
	}

}
