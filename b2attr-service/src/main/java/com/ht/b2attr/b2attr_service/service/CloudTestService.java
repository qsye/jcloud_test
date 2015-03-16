package com.ht.b2attr.b2attr_service.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ht.b2attr.b2attr_service.schema.CloudTest;

public interface CloudTestService {
	@GET
	@Path("/cloudTests")
	@Produces(MediaType.APPLICATION_JSON)
	byte[] retrieveAllCloudTest() throws IOException;

	@GET
	@Path("/cloudTest{id}")
	@Produces(MediaType.APPLICATION_JSON)
	CloudTest retrieveCloudTestById(@PathParam("id") long id);

	@POST
	@Path("/cloudTests")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	byte[] retrieveProductsByName() throws IOException;

	@POST
	@Path("/cloudTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	CloudTest createCloudTest();

	@PUT
	@Path("/product/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	CloudTest updateCloudTestById(@PathParam("id") long id, Map<String, Object> fieldMap);

	@DELETE
	@Path("/product/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	CloudTest deleteCloudTestById(@PathParam("id") long id);

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	String getXml();

	@GET
	@Path("/string")
	@Produces(MediaType.TEXT_HTML)
	String getString();
}
