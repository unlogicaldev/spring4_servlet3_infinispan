package com.spring4.servlet3.sample.common.mongo;


import java.util.Arrays;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.spring4.servlet3.sample.BaseTest;
import com.spring4.servlet3.sample.common.mongo.MongoTemplate;
import com.spring4.servlet3.sample.common.util.DateUtil;

public class MongoTest_01_CRUD extends BaseTest {

	@Test
	public void testCreate() {
		
		DBObject o1 = new BasicDBObject("name", "Rhys Lee")
								.append("age", 29)
								.append("sex", 1)
								.append("type", 6)
								.append("id", "1")
								.append("pass", "1")
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o2 = new BasicDBObject("name", "Ari")
								.append("age", 19)
								.append("sex", 0)
								.append("type", 3)
								.append("id", "2")
								.append("pass", "2")
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o3 = new BasicDBObject("name", "Atrox")
								.append("age", 12)
								.append("sex", 1)
								.append("type", 5)
								.append("id", "3")
								.append("pass", "3")
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o4 = new BasicDBObject("name", "Atrox")
								.append("age", 29)
								.append("sex", 1)
								.append("type", 5)
								.append("id", "4")
								.append("pass", "4")
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));
		
		DBCollection c = MongoTemplate.getDBCollection("TEST");
		c.drop(); //테스트를 위한 초기화
		
		c.createIndex(new BasicDBObject("name",1),"idx_TEST_01",true);
		/*
		 * CRUD - Create
		 */

		System.out.println("****** CRUD - C");
		
		WriteResult wr = c.insert(o1);	
		System.out.println("insert 1 : " + wr);
		System.out.println("insert 1 : " + wr.getError());
		
		wr = c.insert(Arrays.asList(o2,o3));	//by List
		System.out.println("insert 2 : " + wr);
		System.out.println("insert 2 : " + wr.getError());
		
		WriteResult wr2 = null;
		try{
			wr2 = c.insert(o4);	
		}catch(Exception e){
			System.out.println("insert 3 : " + e.getMessage());
			System.out.println("insert 3 : " + wr2);
		}
		
		System.out.println("****** Result");
		DBCursor cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}
		

		/*
		 * CRUD - Update
		 */

		System.out.println("****** CRUD - U");
		
		DBObject q = new BasicDBObject("age",29);
		DBObject u = new BasicDBObject("$set",new BasicDBObject("update",1));
		
		wr = c.update(q, u);
		System.out.println("update 1 : " + wr);
		System.out.println("update 1 : " + wr.getError());

		System.out.println("****** Result1"+q);
		System.out.println("****** Result1"+u);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}

		u = new BasicDBObject("$set",new BasicDBObject("update",2));
		wr = c.update(q, u,false,true);
		System.out.println("update 2 : " + wr);
		System.out.println("update 2 : " + wr.getError());

		System.out.println("****** Result2"+q);
		System.out.println("****** Result2"+u);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}
		
		q = new BasicDBObject("age",34);
		DBObject o5 = new BasicDBObject("name", "Mazie")
								.append("age", 34)
								.append("sex", 1)
								.append("type", 5)
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));
		u = new BasicDBObject("$set", o5);
		
		wr = c.update(q, u);
		System.out.println("update 3 : " + wr);
		System.out.println("update 3 : " + wr.getError());

		System.out.println("****** Result3" + q);
		System.out.println("****** Result3" + u);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}

		wr = c.update(q, u,true,false);
		System.out.println("update 4 : " + wr);
		System.out.println("update 4 : " + wr.getError());

		System.out.println("****** Result4" + q);
		System.out.println("****** Result4" + u);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}
		

		/*
		 * CRUD - Delete
		 */

		System.out.println("****** CRUD - D");
		
		q = new BasicDBObject("age",29);
		
		wr = c.remove(q);
		System.out.println("delete 1 : " + wr);
		System.out.println("delete 1 : " + wr.getError());

		System.out.println("****** Result1" + q);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}

		q = new BasicDBObject("age",new BasicDBObject("$gt", 20));
		wr = c.remove(q,WriteConcern.SAFE);
		System.out.println("delete 2 : " + wr);
		System.out.println("delete 2 : " + wr.getError());
	
		System.out.println("****** Result2" + q);
		cr = c.find();
		while(cr.hasNext()){
			System.out.println(cr.next());
		}
	}
	

}
