package com.spring4.servlet3.sample.config;

import org.infinispan.spring.provider.SpringRemoteCacheManagerFactoryBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class InfinispanConfig {

	@Bean
	public SpringRemoteCacheManagerFactoryBean cacheManager() throws Exception{
		SpringRemoteCacheManagerFactoryBean cacheManager = new SpringRemoteCacheManagerFactoryBean();
		return cacheManager;
	}
}
