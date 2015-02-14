package com.spring4.servlet3.sample.web.member.service;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.spring4.servlet3.sample.common.abs.AbstractBaseService;

@Service("memberService")
public class MemberServiceImpl extends AbstractBaseService<MemberServiceImpl>
		implements MemberService {
	
	@Override
	public DBObject selectMember(String id, String pass) {
		return getDBCollection("TEST").findOne(new BasicDBObject("id", id).append("pass", pass));
	}

}
