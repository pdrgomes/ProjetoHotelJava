package com.fadergs.api.resources.util;

import javax.ws.rs.core.Response;

public class ResponseBuilderControl {
	public static Response.ResponseBuilder allowOrigin(Response.ResponseBuilder responseBuilder) {
        return responseBuilder
                .header("Access-Control-Allow-Origin", "*");
    }
}
