package com.ht.b2attr.b2attr_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Test DI of spring with annotation.
 * @author Cloud_team
 *
 */
@Configuration
public class MyBeanFactory {

	@Bean
	public SocketClient sc2()
	{
		return new SocketClient("localhost", 10000);
	}
}
