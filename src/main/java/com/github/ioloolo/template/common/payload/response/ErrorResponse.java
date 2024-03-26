package com.github.ioloolo.template.common.payload.response;

import lombok.Data;

public class ErrorResponse extends Response<ErrorResponse.ErrorDto> {

	public ErrorResponse(Exception e) {
		super(true, ErrorDto.of(e));
	}

	@Data(staticConstructor = "of")
	public static class ErrorDto {

		private final String message;

		public static ErrorDto of(Exception e) {
			return ErrorDto.of(e.getMessage());
		}
	}
}
