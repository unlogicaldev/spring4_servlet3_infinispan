package com.servlet3.sample.common.abs;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DBCollection;
import com.servlet3.sample.common.mongo.MongoTemplate;

public abstract class AbstractBaseService<T> {

	@SuppressWarnings("unchecked")
	final protected Logger log = LoggerFactory.getLogger(((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));

	@Autowired
	protected CommonSqlDao commonSql;
	
	@Autowired
	protected CommonSqlDao commonSqlPost;
	
	protected DBCollection getDBCollection(String collectionName) {
		return MongoTemplate.getDBCollection(collectionName);
	}
}
