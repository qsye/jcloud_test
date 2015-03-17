package com.ht.b2attr.b2attr_service.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.DAO.CloudTestDao;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.schema.CloudTestsList;
import com.ht.b2attr.b2attr_service.util.DateUtil;

@Component
public class CloudTestServiceImpl implements CloudTestService {

	@Autowired
	private CloudTestDao jdbcCloudTestDAO;
	@Autowired
	private DataFileWriter<SpecificRecordBase> dataFileWriter;

	public byte[] retrieveAllCloudTest() throws IOException {

		System.out.print("Get a request to method:");
		System.out.println("retrieveAllCloudTest");
		List<CloudTest> list = jdbcCloudTestDAO.queryAll();
		// for (int i = 1; i < 3; i++) {
		// CloudTest ct = new CloudTest();
		// ct.setTId(i);
		// ct.setTDt(new Date());
		// ct.setTAttribute("attr");
		// ct.setTDesc("desc");
		// list.add(ct);
		// }
		CloudTestsList cloudTestsList = new CloudTestsList(list);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(CloudTestsList.SCHEMA$, outputStream);
		dataFileWriter.append(cloudTestsList);
		dataFileWriter.close();
		System.out.println(outputStream.toString());

		return outputStream.toByteArray();
	}

	public byte[] retrieveCloudTestById(int id) throws IOException {
		System.out.print("Get a request to method:");
		System.out.println("retrieveCloudTestById");
		// CloudTest ct = new CloudTest();
		// ct.setTId(1);
		// ct.setTDesc("this is a cloud test");
		// ct.setTAttribute("column3");
		// ct.setTDt(new Date());
		// return ct;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(CloudTest.SCHEMA$, outputStream);
		dataFileWriter.append(jdbcCloudTestDAO.queryById(id));
		dataFileWriter.close();
		return outputStream.toByteArray();
	}

	public int createCloudTest(int id, Map<String, Object> fieldMap) throws ParseException {
		System.out.print("Get a request to method:");
		System.out.println("createCloudTest");
		System.out.println(id);
		System.out.println(fieldMap);
		CloudTest ct = new CloudTest();
		ct.setTId(id);
		ct.setTDesc(fieldMap.get("t_desc").toString());
		ct.setTAttribute(fieldMap.get("t_attribute").toString());
		Date dt = DateUtil.parse(fieldMap.get("t_dt").toString());
		ct.setTDt(dt);
		return jdbcCloudTestDAO.insertCloudTest(ct);
	}

	public int updateCloudTestById(int id, Map<String, Object> fieldMap) throws ParseException {
		System.out.print("Get a request to method:");
		System.out.println("updateCloudTestById");
		CloudTest ct = new CloudTest();
		ct.setTId(id);
		ct.setTDesc(fieldMap.get("t_desc").toString());
		ct.setTAttribute(fieldMap.get("t_attribute").toString());
		Date dt = DateUtil.parse(fieldMap.get("t_dt").toString());
		ct.setTDt(dt);
		return jdbcCloudTestDAO.updateCloudTest(ct);
	}

	public Response deleteCloudTestById(int id) {
		System.out.print("Get a request to method:");
		System.out.println("deleteCloudTestById");
		if (jdbcCloudTestDAO.deleteCloudTestById(id) > 0) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
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
