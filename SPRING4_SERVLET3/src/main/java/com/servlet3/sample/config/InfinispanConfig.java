package com.servlet3.sample.config;

import org.infinispan.spring.provider.SpringRemoteCacheManagerFactoryBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

//@Configuration
//@EnableCaching
public class InfinispanConfig {
	
	@Bean
	public SpringRemoteCacheManagerFactoryBean cacheManager() throws Exception{
		SpringRemoteCacheManagerFactoryBean cacheManager = new SpringRemoteCacheManagerFactoryBean();
		String active = System.getProperty("spring.profiles.active", "local");
		cacheManager.setConfigurationPropertiesFileLocation(new DefaultResourceLoader().getResource("classpath:hotrod-client-"+active+".properties"));
		return cacheManager;
	}
}
