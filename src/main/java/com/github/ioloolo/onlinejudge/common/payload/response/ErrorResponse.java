package com.github.ioloolo.onlinejudge.common.payload.response;

import com.github.ioloolo.onlinejudge.common.constant.Error;

public class ErrorResponse extends MessageResponse {

	public ErrorResponse(Error error) {
		super(error.getMessage());
	}

	public ErrorResponse(String errorMessage) {
		super(errorMessage);
	}
}
