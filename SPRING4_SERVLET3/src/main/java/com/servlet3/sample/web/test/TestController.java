package com.servlet3.sample.web.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.servlet3.sample.web.test.service.TestService;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@Autowired TestService testService;
	
	@RequestMapping(value="/1", method = RequestMethod.GET)
	public @ResponseBody String main() throws Exception {

		DBObject o = new BasicDBObject();
		o.put("test", new Date().getTime());
		testService.insertTest(o);
		
		DBObject o1 = testService.selectTest(o.get("id").toString());
		DBObject o2 = testService.selectTest((Integer.parseInt(o.get("id").toString())-1)+"");
		o1.put("o2", o2);
		
		return o1.toString();
	}
	
	@RequestMapping(value="/1/{id}", method = RequestMethod.GET)
	public @ResponseBody String main(@PathVariable String id) throws Exception {

		DBObject o = new BasicDBObject();
		o.put("test", new Date().getTime());
		testService.insertTest(o);
		
		DBObject o1 = testService.selectTest(o.get("id").toString());
		DBObject o2 = testService.selectTest((Integer.parseInt(o.get("id").toString())-1)+"");
		DBObject o3 = testService.selectTest(id);
		o1.put("o2", o2);
		o1.put("o3", o3);
		
		return o1.toString();
	}
}
