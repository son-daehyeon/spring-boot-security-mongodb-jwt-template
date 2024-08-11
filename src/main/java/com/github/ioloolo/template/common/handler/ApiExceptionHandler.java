package com.github.ioloolo.template.common.handler;

import com.github.ioloolo.template.domain.api.dto.response.ApiResponse;
import com.github.ioloolo.template.domain.api.exception.ApiException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ApiResponse<?> apiException(ApiException e) {

		return ApiResponse.createError(e);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

		if (e.getBindingResult().getFieldError() == null) {
			return ApiResponse.createError(new ApiException(e.getMessage()));
		}

		String field = e.getBindingResult().getFieldError().getField();
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		String errorMessage = String.format("%s은(는) %s", field, message);

		return ApiResponse.createError(new ApiException(errorMessage));
	}
}
