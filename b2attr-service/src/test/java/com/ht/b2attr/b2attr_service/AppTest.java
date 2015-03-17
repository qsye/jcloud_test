package com.ht.b2attr.b2attr_service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.cxf.jaxrs.client.WebClient;

import com.alibaba.fastjson.JSON;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Test get all cloud_test
	 * 
	 * @throws IOException
	 */
	public void testApp() throws IOException {
		String baseAddress = "http://172.16.25.37:8080/cloud/rest";
		byte[] result = WebClient.create(baseAddress).path("/cloudTests").accept(MediaType.APPLICATION_JSON).get(byte[].class);
		System.out.println(new String(result));

		DatumReader<SpecificRecordBase> ctDatumReader = new SpecificDatumReader<SpecificRecordBase>();

		SeekableInput sin = new SeekableByteArrayInput(result);
		DataFileReader<SpecificRecordBase> dataFileReader = null;
		try {

			dataFileReader = new DataFileReader<SpecificRecordBase>(sin, ctDatumReader);
			while (dataFileReader.hasNext()) {
				SpecificRecordBase ct = null;
				ct = dataFileReader.next(ct);
				System.out.println(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dataFileReader != null) {
				dataFileReader.close();
			}
		}
		assertTrue(true);
	}

	/**
	 * Test create Cloud_test
	 */
	public void testCreate() {
		String baseAddress = "http://172.16.25.37:8080/cloud/rest";
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("t_attribute", "this is attribute");
		fieldMap.put("t_desc", "this is desc");
		fieldMap.put("t_dt", new Date().toString());

		int result = WebClient.create("http://172.16.25.37:8080/cloud/rest/cCloudTest/10").accept(MediaType.APPLICATION_JSON).header("content-type", "application/json")
				.post(JSON.toJSONString(fieldMap), int.class);
		System.out.println(result);
		assertEquals(1, result);
	}

	public void testUpdate() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("t_attribute", "this is attribute update");
		fieldMap.put("t_desc", "this is desc update");
		fieldMap.put("t_dt", new Date().toString());

		int result = WebClient.create("http://172.16.25.37:8080/cloud/rest/uCloudTest/10").accept(MediaType.APPLICATION_JSON).header("content-type", "application/json")
				.post(JSON.toJSONString(fieldMap), int.class);
		System.out.println(result);
		assertEquals(1, result);
	}

	public void testDelete() {
		String baseAddress = "http://172.16.25.37:8080/cloud/rest";
		Response response = WebClient.create(baseAddress).path("/dCloudTest/10").accept(MediaType.APPLICATION_JSON).delete();
		System.out.println(response.getStatus());
		assertEquals(400, response.getStatus());
	}
}
