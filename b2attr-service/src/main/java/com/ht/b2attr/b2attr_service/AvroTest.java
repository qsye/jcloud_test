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

import com.ht.b2attr.b2attr_service.DAO.BltNoLeverageColumnDao;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumn;
import com.ht.b2attr.b2attr_service.schema.BltNoLeverageColumnList;

/**
 * It is a case of avro test. It test serialize object to avro string and deserialize avro string to object.
 * 
 * @author Cloud_team
 *
 */
public class AvroTest {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		Schema schema = ReflectData.get().getSchema(CloudTest.class);
//		Schema listSchema = ReflectData.get().getSchema(CloudTestsList.class);
		Schema schema =BltNoLeverageColumn.SCHEMA$;
		Schema listSchema=BltNoLeverageColumnList.SCHEMA$;
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		BltNoLeverageColumnDao dao = ctx.getBean("jdbcCloudTestDAO", BltNoLeverageColumnDao.class);
		try {
			System.out.println("multiple===========================");
			testMultiple(listSchema, dao);
			System.out.println("single===========================");
			testSingleJson(schema, dao);
			System.out.println("singleBinary===========================");
			testSingleBinary(schema, dao);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test serialize object to avro string. It could serialize one or any object with single schema.
	 * 
	 * @param schema
	 *            is the schema should be transfer.
	 * @param dao
	 *            is the operater of jdbc.
	 * @throws IOException
	 */
	public static void testMultiple(Schema schema, BltNoLeverageColumnDao dao) throws IOException {

		DatumWriter<BltNoLeverageColumnList> ctDatumWriter = new SpecificDatumWriter<BltNoLeverageColumnList>();
		DataFileWriter<BltNoLeverageColumnList> dataFileWriter = new DataFileWriter<BltNoLeverageColumnList>(ctDatumWriter);

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
		List<BltNoLeverageColumn> list = new ArrayList<BltNoLeverageColumn>();
		// List<CloudTest> list=dao.queryAll();
		for (int i = 0; i < 3; i++) {
			BltNoLeverageColumn ct = new BltNoLeverageColumn();
			ct.setTId(i);
			ct.setTDt(new Date());
			ct.setTAttribute("attr");
			ct.setTDesc("desc");
			// dataFileWriter.append(ct);
			list.add(ct);
		}
		BltNoLeverageColumnList cts = new BltNoLeverageColumnList(list);
		dataFileWriter.append(cts);
		dataFileWriter.close();
		System.out.println(outputStream.toString());
		SeekableInput sin = new SeekableByteArrayInput(outputStream.toByteArray());
		DatumReader<BltNoLeverageColumnList> ctDatumReader = new SpecificDatumReader<BltNoLeverageColumnList>();
		// DataFileReader<CloudTest> dataFileReader = new DataFileReader<CloudTest>(file, ctDatumReader);
		DataFileReader<BltNoLeverageColumnList> dataFileReader = new DataFileReader<BltNoLeverageColumnList>(sin, ctDatumReader);
		while (dataFileReader.hasNext()) {
			BltNoLeverageColumnList ct = null;
			ct = dataFileReader.next(ct);
			System.out.println(ct);
		}
	}

	/**
	 * Test serialize object to avro json string. It could serialize single object.
	 * 
	 * @param schema
	 *            is the schema should be transfer.
	 * @param dao
	 *            is the operater of jdbc.
	 * @throws IOException
	 */
	public static void testSingleJson(Schema schema, BltNoLeverageColumnDao dao) throws IOException {

		DatumWriter<BltNoLeverageColumn> writer = new GenericDatumWriter<BltNoLeverageColumn>();
		writer.setSchema(schema);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().jsonEncoder(schema, out);
		BltNoLeverageColumn ct=dao.queryById(1);
		System.out.println(ct);
		writer.write(ct, encoder);
		encoder.flush();
		out.close();
		System.out.println(out.toString());

		DatumReader<BltNoLeverageColumn> reader = new GenericDatumReader<BltNoLeverageColumn>();
		reader.setSchema(schema);
		Decoder decoder = DecoderFactory.get().jsonDecoder(schema, out.toString());
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result);

	}
	/**
	 * Test serialize object to avro binary string. It could serialize single object.
	 * 
	 * @param schema
	 *            is the schema should be transfer.
	 * @param dao
	 *            is the operater of jdbc.
	 * @throws IOException
	 */
	public static void testSingleBinary(Schema schema, BltNoLeverageColumnDao dao) throws IOException {

		DatumWriter<BltNoLeverageColumn> writer = new GenericDatumWriter<BltNoLeverageColumn>();
		writer.setSchema(schema);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		writer.write(dao.queryById(1), encoder);
		encoder.flush();
		out.close();
		System.out.println(out.toString());
		
		DatumReader<BltNoLeverageColumn> reader = new GenericDatumReader<BltNoLeverageColumn>();
		reader.setSchema(schema);
		Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result);

	}
}
