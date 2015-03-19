package com.ht.b2attr.b2attr_service;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ht.b2attr.b2attr_service.service.BltNoLeverageColumnServiceImpl;

/**
 * It is a case of RESTful/cxf(jetty) test. It start a listener of http.
 *
 * @author Cloud_team
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		testRESTful(ctx);
	}

	public static void testRESTful(ApplicationContext ctx) {
		List<Class<?>> resourceClassList = new ArrayList<Class<?>>();
		resourceClassList.add(BltNoLeverageColumnServiceImpl.class);

		List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
		resourceProviderList.add(new SingletonResourceProvider(ctx.getBean("cloudTestServiceImpl", BltNoLeverageColumnServiceImpl.class)));

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
