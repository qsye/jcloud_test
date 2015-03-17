package com.ht.b2attr.b2attr_service.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ht.b2attr.b2attr_service.logic.Logic;

@Component
public class CloudTestServiceImpl implements CloudTestService {

	@Autowired
	private Logic logic;

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
		return logic.retrieveAllCloudTest();
	}

	@Override
	public byte[] retrieveCloudTestById(int id) throws IOException {
		return logic.retrieveCloudTestById(id);
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
