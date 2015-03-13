package com.ht.b2attr.b2attr_service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.avro.Schema;
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
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ht.b2attr.b2attr_service.DAO.CloudTestDao;
import com.ht.b2attr.b2attr_service.schema.CloudTest;
import com.ht.b2attr.b2attr_service.service.CloudTestServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		testSocket(ctx);

		testJDBC(ctx);

		try {
			testAvro(ctx);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void testSocket(ApplicationContext ctx) {

		SocketClient sc = (SocketClient) ctx.getBean("sc1");

		sc.receive("test message for spring test");

		sc = (SocketClient) ctx.getBean("sc2");

		sc.receive("test message for spring test2");

	}

	private static void testJDBC(ApplicationContext ctx) {
		// ApplicationContext acx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// JDBCOrecle jdbc = (JDBCOrecle)acx.getBean("DBOperate");
		// jdbc.add(4, "column4");
		// CloudTest ds = jdbc.getById(4);
		// System.out.println(ds.getT_id());
		// System.out.println(ds.getT_attribute());
		// spring jdbc
		CloudTestDao dao = ctx.getBean("jdbcCloudTestDAO", CloudTestDao.class);

		CloudTest ct_insert = new CloudTest();
		ct_insert.setTId(4);
		ct_insert.setTDt(new Date());
		ct_insert.setTDesc("test insert with jdbc template");
		ct_insert.setTAttribute("column4");
		System.out.println(dao.insertCloudTest(ct_insert) ? "insert success" : "insert failed");

		CloudTest ct = dao.queryById(4);
		System.out.println(ct.getTAttribute());

		ct_insert.setTAttribute("column41");
		ct_insert.setTDesc("test update");
		ct_insert.setTDt(new Date());
		System.out.println(dao.updateCloudTest(ct_insert) ? "update success" : "update failed");

		System.out.println(dao.deleteCloudTestById(0) ? "delete success" : "delete failed");
		System.out.println(dao.deleteCloudTestById(4) ? "delete success" : "delete failed");
	}

	public static void testAvro(ApplicationContext ctx) throws IOException {
		CloudTestDao dao = ctx.getBean("jdbcCloudTestDAO", CloudTestDao.class);
		Schema schema = ReflectData.get().getSchema(CloudTest.class);
		System.out.println(schema);
		// DatumWriter<CloudTest> ctDatumWriter = new SpecificDatumWriter<CloudTest>(CloudTest.class);
		// DataFileWriter<CloudTest> dataFileWriter = new DataFileWriter<CloudTest>(ctDatumWriter);
		//
		// File dir = new File("d:\\avro\\");
		// if (!dir.exists()) {
		// dir.mkdirs();
		// }
		// File file = new File("d:\\avro\\cloudTest.avro");
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// // dataFileWriter.create(schema, outputStream);
		// dataFileWriter.create(schema, file);
		// dataFileWriter.append(dao.queryById(1));
		// dataFileWriter.append(dao.queryById(2));
		// dataFileWriter.append(dao.queryById(3));
		// dataFileWriter.close();
		//
		// DatumReader<CloudTest> ctDatumReader=new SpecificDatumReader<CloudTest>();
		// DataFileReader<CloudTest> dataFileReader=new DataFileReader<CloudTest>(file, ctDatumReader);
		//
		// while(dataFileReader.hasNext()){
		// CloudTest ct=null;
		// ct=dataFileReader.next(ct);
		// System.out.println(ct);
		// }

		DatumWriter<CloudTest> writer = new GenericDatumWriter<CloudTest>();
		writer.setSchema(schema);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().jsonEncoder(schema, out);
		writer.write(dao.queryById(1), encoder);
		writer.write(dao.queryById(2), encoder);
		writer.write(dao.queryById(3), encoder);
		encoder.flush();

		out.close();

		DatumReader<CloudTest> reader = new GenericDatumReader<CloudTest>();
		reader.setSchema(schema);
		Decoder decoder = DecoderFactory.get().jsonDecoder(schema, out.toString());
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result);
	}

	public static void testRESTful(ApplicationContext ctx) {
		List<Class<?>> resourceClassList = new ArrayList<Class<?>>();
		resourceClassList.add(CloudTestServiceImpl.class);

		List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
		resourceProviderList.add(new SingletonResourceProvider(new CloudTestServiceImpl()));

		List<Object> providerList = new ArrayList<Object>();
		providerList.add(new JacksonJsonProvider());

		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setAddress("http://0.0.0.0:8080/cloud/rest");
		factory.setResourceClasses(resourceClassList);
		factory.setResourceProviders(resourceProviderList);
		factory.setProviders(providerList);
		factory.create();
		System.out.println("rest cloud is published");
	}
}
