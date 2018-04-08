 package org.rajat.mahajan.messenger1.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
 @Consumes(MediaType.TEXT_PLAIN)
 @Produces(MediaType.TEXT_PLAIN)

public class InjectDemoResource {

	@GET
	@Path("/annotations")
	public String testAnnotation(@MatrixParam("param") String matrixParam,
								@HeaderParam("authSessionId") String headerParam,
								@CookieParam("name") String cookie,
								@CookieParam("name2") String cookie2) {//matrix param uses semicolon instead of ?
		return "test "+matrixParam+" HeaderParam Token "+ headerParam+ " CookieValue "+cookie+cookie2;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders header) {
		String path = uriInfo.getAbsolutePath().toString();
		String parameters = uriInfo.getQueryParameters().toString();
		String cookies = header.getCookies().toString();
		return path+"\n"+parameters+"\n"+cookies;
	}
	
}
