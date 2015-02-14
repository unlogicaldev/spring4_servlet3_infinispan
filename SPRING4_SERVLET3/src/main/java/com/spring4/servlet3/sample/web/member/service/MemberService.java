package com.spring4.servlet3.sample.web.member.service;

import com.mongodb.DBObject;

public interface MemberService {

	DBObject selectMember(String id, String pass) throws Exception;
	
}
