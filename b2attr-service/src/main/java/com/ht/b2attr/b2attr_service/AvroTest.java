package com.ht.b2attr.b2attr_service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ht.b2attr.b2attr_service.DAO.CloudTestDao;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.schema.CloudTestsList;

public class AvroTest {

	public static void main(String[] args) {
		Schema schema = ReflectData.get().getSchema(CloudTest.class);
		Schema listSchema = ReflectData.get().getSchema(CloudTestsList.class);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		CloudTestDao dao = ctx.getBean("jdbcCloudTestDAO", CloudTestDao.class);
		try {
			System.out.println("multiple===========================");
			testMultiple(listSchema, dao);
			// System.out.println("single===========================");
			// testSingleJson(schema, dao);
			// System.out.println("singleBinary===========================");
			// testSingleBinary(schema, dao);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testMultiple(Schema schema, CloudTestDao dao) throws IOException {

		DatumWriter<CloudTestsList> ctDatumWriter = new SpecificDatumWriter<CloudTestsList>();
		DataFileWriter<CloudTestsList> dataFileWriter = new DataFileWriter<CloudTestsList>(ctDatumWriter);

		// File dir = new File("d:\\avro\\");
		// if (!dir.exists()) {
		// dir.mkdirs();
		// }
		// File file = new File("d:\\avro\\cloudTest.avro");
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		dataFileWriter.create(schema, outputStream);
		// dataFileWriter.create(schema, file);
		// dataFileWriter.append(dao.queryById(1));
		// dataFileWriter.append(dao.queryById(2));
		// dataFileWriter.append(dao.queryById(3));
		List<CloudTest> list = new ArrayList<CloudTest>();
		for (int i = 0; i < 3; i++) {
			CloudTest ct = new CloudTest();
			ct.setTId(i);
			ct.setTDt(new Date());
			ct.setTAttribute("attr");
			ct.setTDesc("desc");
			// dataFileWriter.append(ct);
			list.add(ct);
		}
		CloudTestsList cts = new CloudTestsList(list);
		dataFileWriter.append(cts);
		dataFileWriter.close();
		System.out.println(outputStream.toString());
		SeekableInput sin = new SeekableByteArrayInput(outputStream.toByteArray());
		DatumReader<CloudTestsList> ctDatumReader = new SpecificDatumReader<CloudTestsList>();
		// DataFileReader<CloudTest> dataFileReader = new DataFileReader<CloudTest>(file, ctDatumReader);
		DataFileReader<CloudTestsList> dataFileReader = new DataFileReader<CloudTestsList>(sin, ctDatumReader);
		while (dataFileReader.hasNext()) {
			CloudTestsList ct = null;
			ct = dataFileReader.next(ct);
			System.out.println(ct);
		}
	}

	public static void testSingleJson(Schema schema, CloudTestDao dao) throws IOException {

		DatumWriter<CloudTest> writer = new GenericDatumWriter<CloudTest>();
		writer.setSchema(schema);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().jsonEncoder(schema, out);
		writer.write(dao.queryById(1), encoder);
		encoder.flush();

		DatumReader<CloudTest> reader = new GenericDatumReader<CloudTest>();
		reader.setSchema(schema);
		Decoder decoder = DecoderFactory.get().jsonDecoder(schema, out.toString());
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result);

	}

	public static void testSingleBinary(Schema schema, CloudTestDao dao) throws IOException {

		DatumWriter<CloudTest> writer = new GenericDatumWriter<CloudTest>();
		writer.setSchema(schema);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		writer.write(dao.queryById(1), encoder);
		encoder.flush();

		DatumReader<CloudTest> reader = new GenericDatumReader<CloudTest>();
		reader.setSchema(schema);
		Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result);

	}
}
