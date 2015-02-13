package com.servlet3.sample.auth.service;

import com.mongodb.DBObject;

public interface AuthService {

	/**
	 * <PRE>
	 * 1. MethodName	:	createAuthInfo
	 * 2. Comment		:	인증정보 최초 생성 ( 로그인 )
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 12:09:29
	 * </PRE>
	 *
	 *	@param auth
	 *	@return
	 *	@throws Exception
	 */
	void createAuthInfo(DBObject auth) throws Exception;
	
	/**
	 * <PRE>
	 * 1. MethodName	:	getAuthInfo
	 * 2. Comment		:	인증정보 조회
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 12:09:43
	 * </PRE>
	 *
	 *	@param key
	 *	@return
	 *	@throws Exception
	 */
	DBObject getAuthInfo(String key) throws Exception;
	
	/**
	 * <PRE>
	 * 1. MethodName	:	removeAuthInfo
	 * 2. Comment		:	인증정보 삭제 ( 로그아웃 )
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 12:09:54
	 * </PRE>
	 *
	 *	@param key
	 *	@throws Exception
	 */
	void removeAuthInfo(String key) throws Exception;
	
	/**
	 * <PRE>
	 * 1. MethodName	:	generateAuthInfo
	 * 2. Comment		:	인증정보 등록 ( 로그인 )
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 12:10:11
	 * </PRE>
	 *
	 *	@param key
	 *	@return
	 *	@throws Exception
	 */
	DBObject generateAuthInfo(String key) throws Exception;
	
	/**
	 * <PRE>
	 * 1. MethodName	:	reGenerateAuthInfo
	 * 2. Comment		:	인증정보 갱신 ( 세션유지 )
	 * 3. 작성자			:	goodrhys
	 * 4. 작성일			:	2013. 9. 4.	오후 12:10:23
	 * </PRE>
	 *
	 *	@param key, newKey
	 *	@return
	 *	@throws Exception
	 */
	DBObject reGenerateAuthInfo(String key, String newKey) throws Exception;
}
