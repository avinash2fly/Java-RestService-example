package com.avinash;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.avinash.service.RService;

@Path("/helloworld")
public class RController {

	private static RService service = new RService();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{name}")
	public String getGreetings(@Context HttpHeaders httpHeaders, @PathParam("name") String name) {
		return "Hello World" + name;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getcabcount")
	public Response getDriverCount(@Context HttpHeaders httpHeaders, JSONObject data) throws Exception {
		JSONObject out = new JSONObject();
		if (data.has("cabId") && data.has("date")) {

			out.put("count", service.getCabCount(data.getString("cabId"), data.getString("date"),
					data.has("cache") ? data.getBoolean("cache") : false));
			return Response.ok(out).build();
		}
		out.put("error", "missing parameters");
		return Response.ok(out).build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getcabscount")
	public Response getDriversCount(@Context HttpHeaders httpHeaders, JSONObject data) throws Exception {

		JSONObject out = new JSONObject();
		if (data.has("cabIds") && data.has("date")) {
			out.put("data", service.getCabsCount(data.getJSONArray("cabIds"), data.getString("date"),
					data.has("cache") ? data.getBoolean("cache") : false));
			return Response.ok(out).build();
		}
		out.put("error", "missing parameters");
		return Response.ok(out).build();
	}
}
