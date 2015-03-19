package com.ht.b2attr.b2attr_service;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ht.b2attr.b2attr_service.DAO.BltNoLeverageColumnDao;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn;
/**
 * It is a case of spring jdbc template test. It test insert/update/delete/query from DB.
 * 
 * @author Cloud_team.
 *
 */

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
		BltNoLeverageColumnDao dao = ctx.getBean("BltNoLeverageColumnDao", BltNoLeverageColumnDao.class);

		BltNoLeverageColumn ct_insert = new BltNoLeverageColumn();
		ct_insert.setTId(4);
		ct_insert.setTDt(new Date());
		ct_insert.setTDesc("test insert with jdbc template");
		ct_insert.setTAttribute("column4");
		System.out.println(dao.insertBltNoLeverageColumn(ct_insert) > 0 ? "insert success" : "insert failed");
		System.out.println();

		List<BltNoLeverageColumn> list = dao.queryAll();
		System.out.println("cloud list:" + list);
		System.out.println();
		BltNoLeverageColumn ct = dao.queryById(4);
		System.out.println(ct.getTAttribute());
		System.out.println();

		ct_insert.setTAttribute("column41");
		ct_insert.setTDesc("test update");
		ct_insert.setTDt(new Date());
		System.out.println(dao.updateBltNoLeverageColumn(ct_insert) > 0 ? "update success" : "update failed");
		System.out.println();

		System.out.println(dao.deleteBltNoLeverageColumnById(0) > 0 ? "delete success" : "delete failed");
		System.out.println();

		System.out.println(dao.deleteBltNoLeverageColumnById(4) > 0 ? "delete success" : "delete failed");
	}

}
