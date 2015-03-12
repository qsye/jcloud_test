package com.ht.b2attr.b2attr_service.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

@Component
public class CloudTestServiceImpl implements CloudTestService {

	public List<CloudTest> retrieveAllCloudTest() {
		System.out.print("Get a request to method:");
		System.out.println("retrieveAllCloudTest");
		List<CloudTest> list = new ArrayList<CloudTest>();
		CloudTest ds = new CloudTest();
		ds.setT_id(1);
		ds.setT_desc("this is a cloud test");
		ds.setT_attribute("column3");
		ds.setT_dt(new Date(System.currentTimeMillis()));
		list.add(ds);
		return list;
	}

	public CloudTest retrieveCloudTestById(long id) {
		System.out.print("Get a request to method:");
		System.out.println("retrieveCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setT_id(1);
		ct.setT_desc("this is a cloud test");
		ct.setT_attribute("column3");
		ct.setT_dt(new Date(System.currentTimeMillis()));
		return ct;
	}

	public List<CloudTest> retrieveProductsByName() {
		System.out.print("Get a request to method:");
		System.out.println("retrieveProductsByName");
		List<CloudTest> list = new ArrayList<CloudTest>();
		CloudTest ct = new CloudTest();
		ct.setT_id(1);
		ct.setT_desc("this is a cloud test");
		ct.setT_attribute("column3");
		ct.setT_dt(new Date(System.currentTimeMillis()));
		list.add(ct);
		return list;
	}

	public CloudTest createCloudTest() {
		System.out.print("Get a request to method:");
		System.out.println("createCloudTest");
		CloudTest ct = new CloudTest();
		ct.setT_id(1);
		ct.setT_desc("this is a cloud test");
		ct.setT_attribute("column3");
		ct.setT_dt(new Date(System.currentTimeMillis()));
		return ct;
	}

	public CloudTest updateCloudTestById(long id, Map<String, Object> fieldMap) {
		System.out.print("Get a request to method:");
		System.out.println("updateCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setT_id(1);
		ct.setT_desc("this is a cloud test");
		ct.setT_attribute("column3");
		ct.setT_dt(new Date(System.currentTimeMillis()));
		return ct;
	}

	public CloudTest deleteCloudTestById(long id) {
		System.out.print("Get a request to method:");
		System.out.println("deleteCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setT_id(1);
		ct.setT_desc("this is a cloud test");
		ct.setT_attribute("column3");
		ct.setT_dt(new Date(System.currentTimeMillis()));
		return ct;
	}

	public String getXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b>this is xml</b></a>";
		return xml;
	}

	public String getString() {
		String str = "this is a String";
		return str;
	}

}
