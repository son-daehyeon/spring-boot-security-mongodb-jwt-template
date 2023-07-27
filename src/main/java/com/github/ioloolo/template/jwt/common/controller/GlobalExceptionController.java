package com.github.ioloolo.template.jwt.common.controller;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.ioloolo.template.jwt.common.constant.Error;
import com.github.ioloolo.template.jwt.common.payload.response.ErrorResponse;
import com.github.ioloolo.template.jwt.common.payload.response.ValidateErrorResponse;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<? extends ErrorResponse> handleException(Exception e) {
		if (e instanceof Errors) {
			return ResponseEntity.badRequest()
					.body(new ValidateErrorResponse(Objects.requireNonNull(((Errors) e).getFieldError())));
		}

		return Error.fromClass(e.getClass())
				.map(error -> ResponseEntity.internalServerError()
						.body(new ErrorResponse(error)))
				.orElseGet(() -> ResponseEntity.internalServerError()
						.body(new ErrorResponse(e.getMessage())));
	}
}
