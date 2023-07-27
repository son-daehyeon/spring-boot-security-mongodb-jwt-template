package com.github.ioloolo.template.jwt.common.payload.response;

import com.github.ioloolo.template.jwt.common.constant.Error;

public class ErrorResponse extends MessageResponse {

	public ErrorResponse(Error error) {
		super(error.getMessage());
	}

	public ErrorResponse(String error) {
		super(error);
	}
}
