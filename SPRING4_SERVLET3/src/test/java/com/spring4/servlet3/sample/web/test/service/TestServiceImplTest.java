package com.spring4.servlet3.sample.web.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring4.servlet3.sample.BaseTest;
import com.spring4.servlet3.sample.web.test.service.TestService;

public class TestServiceImplTest  extends BaseTest{
	
	@Autowired TestService testService;

	@Test
	public void testSelectTestList() throws Exception {
		testService.selectTestList(null);
		testService.selectTestListPost(null);
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTestListPost() {
		try {
			testService.selectTestListPost(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
