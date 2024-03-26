package com.github.ioloolo.template.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.ioloolo.template.common.payload.response.ErrorResponse;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		return ResponseEntity.internalServerError().body(new ErrorResponse(e));
	}
}
