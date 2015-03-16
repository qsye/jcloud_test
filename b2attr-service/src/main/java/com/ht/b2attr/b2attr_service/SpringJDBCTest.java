package com.ht.b2attr.b2attr_service;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ht.b2attr.b2attr_service.DAO.CloudTestDao;
import com.ht.b2attr.b2attr_service.schema.CloudTest;

public class SpringJDBCTest {

	public static void main(String[] args) {
		testJDBC(new ClassPathXmlApplicationContext("applicationContext.xml"));
	}

	private static void testJDBC(ApplicationContext ctx) {
		// ApplicationContext acx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// JDBCOrecle jdbc = (JDBCOrecle)acx.getBean("DBOperate");
		// jdbc.add(4, "column4");
		// CloudTest ds = jdbc.getById(4);
		// System.out.println(ds.getT_id());
		// System.out.println(ds.getT_attribute());
		// spring jdbc
		CloudTestDao dao = ctx.getBean("jdbcCloudTestDAO", CloudTestDao.class);

		CloudTest ct_insert = new CloudTest();
		ct_insert.setTId(4);
		ct_insert.setTDt(new Date());
		ct_insert.setTDesc("test insert with jdbc template");
		ct_insert.setTAttribute("column4");
		System.out.println(dao.insertCloudTest(ct_insert) > 0 ? "insert success" : "insert failed");

		List<CloudTest> list = dao.queryAll();
		System.out.println("cloud list:" + list);
		CloudTest ct = dao.queryById(4);
		System.out.println(ct.getTAttribute());

		ct_insert.setTAttribute("column41");
		ct_insert.setTDesc("test update");
		ct_insert.setTDt(new Date());
		System.out.println(dao.updateCloudTest(ct_insert) > 0 ? "update success" : "update failed");

		System.out.println(dao.deleteCloudTestById(0) > 0 ? "delete success" : "delete failed");
		System.out.println(dao.deleteCloudTestById(4) > 0 ? "delete success" : "delete failed");
	}

}
