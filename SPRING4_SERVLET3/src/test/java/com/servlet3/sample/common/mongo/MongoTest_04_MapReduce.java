package com.servlet3.sample.common.mongo;


import java.util.Arrays;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.servlet3.sample.BaseTest;
import com.servlet3.sample.common.util.DateUtil;

public class MongoTest_04_MapReduce extends BaseTest {

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
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o3 = new BasicDBObject("name", "Atrox")
								.append("age", 30)
								.append("sex", 1)
								.append("type", 5)
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o4 = new BasicDBObject("name", "Atrox1")
								.append("age", 35)
								.append("sex", 1)
								.append("type", 5)
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));

		DBObject o5 = new BasicDBObject("name", "Atrox2")
								.append("age", 25)
								.append("sex", 1)
								.append("type", 5)
								.append("regDate", DateUtil.getToday("yyyyMMddHHmmss"));
		
		DBCollection c = MongoTemplate.getDBCollection("TEST");
		c.drop(); //테스트를 위한 초기화
		c.insert(Arrays.asList(o1,o2,o3,o4,o5));
		
		String map = "function() { " 
				+ "var category; "
                + "if ( this.age >= 30 ) " + "category = 'Big'; "
                + "else " + "category = 'Small'; "
                + "emit(category, {name: this.name});}";
		
	   String reduce = "function(key, values) { " 
			   		+ "var sum = 0; "
	                + "values.forEach(function(doc) { sum += 1; " + "}); "
	                + "return {cnt : sum};} ";
	   
	   MapReduceCommand cmd = new MapReduceCommand(c, map,
	                reduce, null, MapReduceCommand.OutputType.INLINE, null);
	   MapReduceOutput out = c.mapReduce(cmd);
	   System.out.println("Mapreduce results");
	   for (DBObject o : out.results()) {
	         System.out.println(o.toString());
	   }
	}

}
