package com.github.ioloolo.template.jwt_auth.common.payload.response;

import java.util.Map;

public class ErrorResponse extends KVResponse {

	public ErrorResponse(Exception exception) {
		super(true, Map.of("error", exception.getMessage()));
	}
}
