package com.ht.b2attr.b2attr_service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void test() {
		long id = testCreate();
		if (id <= 0) {
			System.out.println("insert failed  id=" + id);
			Assert.assertTrue(false);
		}
		System.out.println("insert success  id=" + id);
		int resultUpdate = testUpdate(id);
		if (resultUpdate <= 0) {
			System.out.println("update failed  result=" + resultUpdate);
			Assert.assertTrue(false);
		}
		System.out.println("update success  id=" + id);
		testDelete(id);
		System.out.println("delete success");
	}

	@Test
	public void testApp() throws IOException {
		String baseAddress = "http://172.16.25.37:8080/cloud/rest";
		byte[] result = WebClient.create(baseAddress).path("/BltNoLColumns").accept(MediaType.APPLICATION_JSON).get(byte[].class);
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
				System.out.println(ct.getClass().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dataFileReader != null) {
				dataFileReader.close();
			}
		}
		Assert.assertTrue(true);
	}

	public long testCreate() {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("t_attribute", "this is attribute");
		fieldMap.put("t_desc", "this is desc");
		fieldMap.put("t_dt", new Date().toString());

		long result = WebClient.create("http://172.16.25.37:8080/cloud/rest/BltNoLColumns/0").accept(MediaType.APPLICATION_JSON).header("content-type", "application/json")
				.post(JSON.toJSONString(fieldMap), int.class);
		System.out.println(result);
		return result;
	}

	public int testUpdate(long id) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("t_attribute", "this is attribute update");
		fieldMap.put("t_desc", "this is desc update");
		fieldMap.put("t_dt", new Date().toString());

		int result = WebClient.create("http://172.16.25.37:8080/cloud/rest/BltNoLColumns/" + id).accept(MediaType.APPLICATION_JSON).header("content-type", "application/json")
				.post(JSON.toJSONString(fieldMap), int.class);
		System.out.println(result);
		return result;
	}

	public void testDelete(long id) {
		String baseAddress = "http://172.16.25.37:8080/cloud/rest";
		Response response = WebClient.create(baseAddress).path("/BltNoLColumns/" + id).accept(MediaType.APPLICATION_JSON).delete();
		System.out.println(response.getStatus());
		Assert.assertEquals(200, response.getStatus());
	}
}
