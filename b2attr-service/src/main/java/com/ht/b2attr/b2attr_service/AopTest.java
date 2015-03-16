package com.ht.b2attr.b2attr_service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		testSocket(new ClassPathXmlApplicationContext("applicationContext.xml"));
	}
	private static void testSocket(ApplicationContext ctx) {

		SocketClient sc = (SocketClient) ctx.getBean("sc1");

		sc.receive("test message for spring test");

		sc = (SocketClient) ctx.getBean("sc2");

		sc.receive("test message for spring test2");

	}

}
