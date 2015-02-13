package com.servlet3.sample.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.servlet3.sample.BaseTest;

public class InfinispanTest extends BaseTest{
	
	@Autowired CacheManager cacheManager;
	

	@Test
	public void test() throws Exception {
    	//obtain a handle to the remote default cache
		Cache cache = cacheManager.getCache("default");
		 
		//now add something to the cache and make sure it is there
		cache.put("car", new BasicDBObject("name","hundai").append("price", 10000000));
		assertEquals("hundai", ((DBObject)cache.get("car").get()).get("name"));
		log.debug("car is : "+((DBObject)cache.get("car").get()).toString());
		cache.evict("car");
		assertEquals(null, cache.get("car"));
		fail("Not yet implemented");
	}

}
