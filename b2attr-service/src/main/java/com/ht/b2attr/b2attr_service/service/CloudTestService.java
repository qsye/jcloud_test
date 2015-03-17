package com.ht.b2attr.b2attr_service.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * The http service.
 * @author Cloud_team
 *
 */
public interface CloudTestService {
	@GET
	@Path("/cloudTests")
	@Produces(MediaType.APPLICATION_JSON)
	byte[] retrieveAllCloudTest() throws IOException;

	@GET
	@Path("/cloudTest{id}")
	@Produces(MediaType.APPLICATION_JSON)
	byte[] retrieveCloudTestById(@PathParam("id") int id) throws IOException;

	@POST
	@Path("/cCloudTest/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	int createCloudTest(@PathParam("id") int id, Map<String, Object> fieldMap) throws ParseException;

	@POST
	@Path("/uCloudTest/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	int updateCloudTestById(@PathParam("id") int id, Map<String, Object> fieldMap) throws ParseException;

	@DELETE
	@Path("/dCloudTest/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Response deleteCloudTestById(@PathParam("id") int id);

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	String getXml();

	@GET
	@Path("/string")
	@Produces(MediaType.TEXT_HTML)
	String getString();
}
