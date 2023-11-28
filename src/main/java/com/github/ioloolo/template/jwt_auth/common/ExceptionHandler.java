package com.github.ioloolo.template.jwt_auth.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.github.ioloolo.template.jwt_auth.common.payload.response.ErrorResponse;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		return ResponseEntity
			.internalServerError()
			.body(new ErrorResponse(e));
	}
}
