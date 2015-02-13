package com.servlet3.sample.common.mongo;


import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.servlet3.sample.BaseTest;

public class MongoTemplateTest extends BaseTest {

	@Test
	public void test() {
		for(String s:MongoTemplate.getDB().getCollectionNames())
			System.out.println(s);
	}

}
