package com.ht.b2attr.b2attr_service.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.logic.Logic;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.schema.CloudTestsList;

@Component
public class CloudTestServiceImpl implements CloudTestService {

	@Autowired
	private Logic logic;

	// injection by spring
	@Autowired
	private DataFileWriter<SpecificRecordBase> dataFileWriter;

	public String getXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><a><b>this is xml</b></a>";
		return xml;
	}

	public String getString() {
		String str = "this is a String";
		return str;
	}

	@Override
	public byte[] retrieveAllCloudTest() throws IOException {

		CloudTestsList cloudTestsList = logic.retrieveAllCloudTest();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(CloudTestsList.SCHEMA$, outputStream);
		dataFileWriter.append(cloudTestsList);
		dataFileWriter.close();
		System.out.println(outputStream.toString());

		return outputStream.toByteArray();
	}

	@Override
	public byte[] retrieveCloudTestById(int id) throws IOException {
		CloudTest ct = logic.retrieveCloudTestById(id);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(CloudTest.SCHEMA$, outputStream);
		dataFileWriter.append(ct);
		dataFileWriter.close();
		return outputStream.toByteArray();
	}

	@Override
	public int createCloudTest(int id, Map<String, Object> fieldMap) throws ParseException {
		return logic.createCloudTest(id, fieldMap);
	}

	@Override
	public int updateCloudTestById(int id, Map<String, Object> fieldMap) throws ParseException {
		return logic.updateCloudTestById(id, fieldMap);
	}

	@Override
	public Response deleteCloudTestById(int id) {
		return logic.deleteCloudTestById(id);
	}

}
