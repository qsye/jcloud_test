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
		//testSocket();
		
		testJDBC();
		
		//System.gc();
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
	
	private static void testJDBC()
	{
        ApplicationContext acx = new ClassPathXmlApplicationContext("applicationContext.xml");
        JDBCOrecle jdbc = (JDBCOrecle)acx.getBean("DBOperate"); 
        //新增记录
        jdbc.add(4, "column4");
        //根据ID号查询
        DataStruct ds = jdbc.getById(4);
        System.out.println(ds.getT_id());
        System.out.println(ds.getT_attribute());
	}

}
