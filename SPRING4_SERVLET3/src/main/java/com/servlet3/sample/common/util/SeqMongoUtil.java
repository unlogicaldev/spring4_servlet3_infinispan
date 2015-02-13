package com.servlet3.sample.common.util;

import com.mongodb.BasicDBObject;
import com.servlet3.sample.common.mongo.MongoTemplate;

public class SeqMongoUtil {

	/**
	 * <PRE>
	 * 1. MethodName	:	getSeq
	 * 2. Comment		:	Lont type seq
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 3.	오후 4:55:59
	 * </PRE>
	 *
	 *	@param key
	 *	@return
	 */
	public static long getSeq(String key){
			try{
				return (Long) MongoTemplate.getDBCollection("Seq").findAndModify(new BasicDBObject("key",key),new BasicDBObject("seq",1),null,false,new BasicDBObject("$inc",new BasicDBObject("seq",new Long(1))),true,true).get("seq");
			}catch(Exception e){
				return 0L;
			}
	}

	/**
	 * <PRE>
	 * 1. MethodName	:	getSeqInt
	 * 2. Comment		:	Integer type seq
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 3.	오후 4:56:09
	 * </PRE>
	 *
	 *	@param key
	 *	@return
	 */
	public static int getSeqInt(String key){
			try{
				return (Integer) MongoTemplate.getDBCollection("Seq").findAndModify(new BasicDBObject("key",key),new BasicDBObject("seq",1),null,false,new BasicDBObject("$inc",new BasicDBObject("seq",new Integer(1))),true,true).get("seq");
			}catch(Exception e){
				return 0;
			}
	}
}
