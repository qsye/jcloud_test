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
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList;

@Component
public class BltNoLeverageColumnServiceImpl implements BltNoLeverageColumnService {

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
	public byte[] retrieveAllBltNoLColumns() throws IOException {

		BltNoLeverageColumnList cloudTestsList = logic.retrieveAllCloudTest();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(BltNoLeverageColumnList.SCHEMA$, outputStream);
		dataFileWriter.append(cloudTestsList);
		dataFileWriter.close();
		System.out.println(outputStream.toString());

		return outputStream.toByteArray();
	}

	@Override
	public byte[] retrieveBltNoLColumn(int id) throws IOException {
		BltNoLeverageColumn ct = logic.retrieveCloudTestById(id);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		dataFileWriter.create(BltNoLeverageColumn.SCHEMA$, outputStream);
		dataFileWriter.append(ct);
		dataFileWriter.close();
		return outputStream.toByteArray();
	}

	@Override
	public long updateBltNoColumn(int id, Map<String, Object> fieldMap) throws ParseException {
		if (id == 0) {
			int newId = logic.createCloudTest(fieldMap);
			return newId;
		} else {
			return logic.updateCloudTestById(id, fieldMap);
		}

	}

	@Override
	public Response deleteBltNoLColumn(int id) {
		return logic.deleteCloudTestById(id);
	}

}
