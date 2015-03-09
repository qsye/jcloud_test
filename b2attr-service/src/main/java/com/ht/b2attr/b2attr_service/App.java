package com.ht.b2attr.b2attr_service;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		testSocket();

		System.gc();
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testSocket() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		SocketClient sc = (SocketClient) ctx.getBean("sc1");

		sc.receive("test message for spring test");	
		
		sc = (SocketClient) ctx.getBean("sc2");

		sc.receive("test message for spring test2");	
		
	}
	
	

}
