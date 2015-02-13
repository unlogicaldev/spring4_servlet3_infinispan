package com.servlet3.sample;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.servlet3.sample.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("local")
@ContextConfiguration(classes = {WebConfig.class})
public abstract class BaseTest extends TestCase {
	
	final protected Logger log = LoggerFactory.getLogger("TEST");
}
