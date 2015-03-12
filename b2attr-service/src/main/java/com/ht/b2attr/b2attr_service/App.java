package com.ht.b2attr.b2attr_service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ht.b2attr.b2attr_service.DAO.JdbcCloudTestDAO;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.service.CloudTestServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// testSocket();

		testJDBC();

		List<Class<?>> resourceClassList = new ArrayList<Class<?>>();
		resourceClassList.add(CloudTestServiceImpl.class);

		List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
		resourceProviderList.add(new SingletonResourceProvider(new CloudTestServiceImpl()));

		List<Object> providerList = new ArrayList<Object>();
		providerList.add(new JacksonJsonProvider());

		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setAddress("http://0.0.0.0:8080/cloud/rest");
		factory.setResourceClasses(resourceClassList);
		factory.setResourceProviders(resourceProviderList);
		factory.setProviders(providerList);
		factory.create();
		System.out.println("rest cloud is published");

	}

	private static void testSocket() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		SocketClient sc = (SocketClient) ctx.getBean("sc1");

		sc.receive("test message for spring test");

		sc = (SocketClient) ctx.getBean("sc2");

		sc.receive("test message for spring test2");

	}

	private static void testJDBC() {
		// ApplicationContext acx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// JDBCOrecle jdbc = (JDBCOrecle)acx.getBean("DBOperate");
		// //新增记录
		// jdbc.add(4, "column4");
		// //根据ID号查询
		// CloudTest ds = jdbc.getById(4);
		// System.out.println(ds.getT_id());
		// System.out.println(ds.getT_attribute());
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// spring jdbc
		JdbcCloudTestDAO dao = new JdbcCloudTestDAO();
		dao.setJdbcTemplate((SimpleJdbcTemplate) ctx.getBean("jdbcTemplate"));
		CloudTest ct = dao.queryById(1);
		System.out.println(ct.getT_attribute());

		CloudTest ct_insert = new CloudTest();
		ct_insert.setT_id(4);
		ct_insert.setT_dt(new Date(System.currentTimeMillis()));
		ct_insert.setT_desc("test insert with jdbc template");
		ct_insert.setT_attribute("column4");
		System.out.println(dao.insertCloudTest(ct_insert) ? "insert success" : "insert failed");

		ct_insert.setT_attribute("column41");
		ct_insert.setT_desc("test update");
		ct_insert.setT_dt(new Date(System.currentTimeMillis()));
		System.out.println(dao.updateCloudTest(ct_insert) ? "update success" : "update failed");

		System.out.println(dao.deleteCloudTestById(0) ? "delete success" : "delete failed");
		System.out.println(dao.deleteCloudTestById(4) ? "delete success" : "delete failed");
	}

}
