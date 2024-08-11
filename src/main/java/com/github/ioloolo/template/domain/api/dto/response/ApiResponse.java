package com.github.ioloolo.template.domain.api.dto.response;

import com.github.ioloolo.template.domain.api.exception.ApiException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class ApiResponse<T> {

	private final boolean error;
	private final T       content;

	private ApiResponse(boolean error, T content) {

		this.error = error;
		this.content = content;
	}

	public static <T> ApiResponse<T> createSuccess(T content) {

        //noinspection unchecked
        return new ApiResponse<>(false, Objects.requireNonNullElseGet(content, () -> (T) new HashMap<>()));
    }

	public static ApiResponse<Map<String, String>> createError(ApiException exception) {

		return new ApiResponse<>(true, Map.of("message", exception.getMessage()));
	}
}
