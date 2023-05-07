package com.github.ioloolo.onlinejudge.common.payload.response;

import org.springframework.validation.FieldError;

public class ValidateErrorResponse extends ErrorResponse {

	public ValidateErrorResponse(FieldError error) {
		super(error.getField() + "은/는 " + error.getDefaultMessage());
	}
}
