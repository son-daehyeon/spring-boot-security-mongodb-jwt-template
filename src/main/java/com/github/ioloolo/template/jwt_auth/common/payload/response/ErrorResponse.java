package com.github.ioloolo.template.jwt_auth.common.payload.response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Data;

public class ErrorResponse extends Response<ErrorResponse.ErrorDto> {

	public ErrorResponse(Exception e) {
		super(true, ErrorDto.of(e));
	}

	@ControllerAdvice
	public static class Handler {

		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleException(Exception e) {
			return ResponseEntity.internalServerError().body(new ErrorResponse(e));
		}
	}

	@Data(staticConstructor = "of")
	public static class ErrorDto {

		private final String message;

		public static ErrorDto of(Exception e) {
			return ErrorDto.of(e.getMessage());
		}
	}
}
