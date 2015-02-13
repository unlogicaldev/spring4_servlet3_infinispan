package com.servlet3.sample.common.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoTemplate {
	
	private static DB db;	
	private static MongoClient mongoClient;
			 
	/**
	 * @param dbName
	 * @param username
	 * @param password
	 * @param host
	 * @param port
	 * @param autoConnectRetry
	 * @param connectionsPerHost
	 * @param connectTimeout
	 * @param maxWaitTime
	 * @param threadsAllowedToBlockForConnectionMultiplier
	 * @param socketTimeout
	 */
	public MongoTemplate(String dbName,
						  String username, 
						  String password,
						  String host, 
						  int port, 
						  boolean autoConnectRetry, 
						  int connectionsPerHost, 
						  int connectTimeout, 
						  int maxWaitTime, 
						  int threadsAllowedToBlockForConnectionMultiplier, 
						  int socketTimeout) {
		
		if(mongoClient == null){
			Builder b = MongoClientOptions.builder();
			b.autoConnectRetry(autoConnectRetry);
			b.connectionsPerHost(connectionsPerHost);
			b.connectTimeout(connectTimeout);
			b.maxWaitTime(maxWaitTime);
			b.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
			b.socketTimeout(socketTimeout);
			MongoClientOptions options = b.build();
			try {
				List<ServerAddress> list = new ArrayList<ServerAddress>();
				for(String h : host.split(";")){
					list.add(new ServerAddress(h.split(":")[0], Integer.parseInt(h.split(":")[1])));
				}
				mongoClient = new MongoClient(list,options);
				mongoClient.setWriteConcern(WriteConcern.SAFE);
				db = mongoClient.getDB(dbName);
				db.authenticate(username, password.toCharArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <PRE>
	 * 1. MethodName	:	getDBCollection
	 * 2. Comment		:	DBCollection
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 8. 20.	오전 10:35:20
	 * </PRE>
	 *
	 *	@param collectionName
	 *	@return
	 */
	public static DBCollection getDBCollection(String collectionName){
		return db.getCollection(collectionName);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName	:	getDB
	 * 2. Comment		:   DB 
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 12. 12.	오후 9:28:56
	 * </PRE>
	 *
	 *	@param collectionName
	 *	@return
	 */
	public static DB getDB(){
		return db;
	}
}
