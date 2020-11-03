package org.example.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("/annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("authSessionID") String header,
                                            @CookieParam("name") String cookie) {
        return "MatrixParam: " + matrixParam + " " + "HeaderParam: " + header + " " + "CookieParam: " + cookie;
    }

    @GET
    @Path("/context")
    public String getParamsUsingContext(@Context UriInfo uriInfo,
                                        @Context HttpHeaders httpHeaders ) {
        String path = uriInfo.getAbsolutePath().toString();
        String headers = httpHeaders.getCookies().toString();
        return "Path: " + path + " Cookies: " + headers;
    }
}
