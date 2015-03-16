package com.ht.b2attr.b2attr_service.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.DAO.CloudTestDao;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.schema.CloudTestsList;

@Component
public class CloudTestServiceImpl implements CloudTestService {

	@Autowired
	private CloudTestDao jdbcCloudTestDAO;
	@Autowired
	private DataFileWriter<SpecificRecordBase> dataFileWriter;

	public byte[] retrieveAllCloudTest() throws IOException {

		System.out.print("Get a request to method:");
		System.out.println("retrieveAllCloudTest");
		List<CloudTest> list = new ArrayList<CloudTest>();
		for (int i = 1; i < 3; i++) {
			CloudTest ct = new CloudTest();
			ct.setTId(i);
			ct.setTDt(new Date());
			ct.setTAttribute("attr");
			ct.setTDesc("desc");
			list.add(ct);
//			list.add(jdbcCloudTestDAO.queryById(i));
		}
		CloudTestsList cloudTestsList = new CloudTestsList(list);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(CloudTestsList.SCHEMA$, outputStream);
		dataFileWriter.append(cloudTestsList);
		dataFileWriter.close();
		System.out.println(outputStream.toString());

		return outputStream.toByteArray();
	}

	public CloudTest retrieveCloudTestById(long id) {
		System.out.print("Get a request to method:");
		System.out.println("retrieveCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setTId(1);
		ct.setTDesc("this is a cloud test");
		ct.setTAttribute("column3");
		ct.setTDt(new Date());
		return ct;
	}

	public byte[] retrieveProductsByName() throws IOException {
		System.out.print("Get a request to method:");
		System.out.println("retrieveProductsByName");
		List<CloudTest> list = new ArrayList<CloudTest>();
		CloudTestsList cloudTestsList=new CloudTestsList();
		CloudTest ct = new CloudTest();
		ct.setTId(1);
		ct.setTDesc("this is a cloud test");
		ct.setTAttribute("column3");
		ct.setTDt(new Date());
		list.add(ct);
		cloudTestsList.setList(list);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		dataFileWriter.create(cloudTestsList.SCHEMA$, outputStream);
		dataFileWriter.append(cloudTestsList);
		dataFileWriter.close();
		return outputStream.toByteArray();
	}

	public CloudTest createCloudTest() {
		System.out.print("Get a request to method:");
		System.out.println("createCloudTest");
		CloudTest ct = new CloudTest();
		ct.setTId(1);
		ct.setTDesc("this is a cloud test");
		ct.setTAttribute("column3");
		ct.setTDt(new Date());
		return ct;
	}

	public CloudTest updateCloudTestById(long id, Map<String, Object> fieldMap) {
		System.out.print("Get a request to method:");
		System.out.println("updateCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setTId(1);
		ct.setTDesc("this is a cloud test");
		ct.setTAttribute("column3");
		ct.setTDt(new Date());
		return ct;
	}

	public CloudTest deleteCloudTestById(long id) {
		System.out.print("Get a request to method:");
		System.out.println("deleteCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setTId(1);
		ct.setTDesc("this is a cloud test");
		ct.setTAttribute("column3");
		ct.setTDt(new Date());
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
