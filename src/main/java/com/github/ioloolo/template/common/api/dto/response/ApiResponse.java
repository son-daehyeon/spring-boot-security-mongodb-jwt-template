package com.github.ioloolo.template.common.api.dto.response;

import com.github.ioloolo.template.common.api.exception.ApiException;
import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse<T> {

    private final int    statusCode;
    private final String error;
    private final T      content;

    private ApiResponse(int statusCode, String error, T content) {

        this.statusCode = statusCode;
        this.error = error;
        this.content = content;
    }

    public static <T> ApiResponse<T> createSuccess(T content) {

        return new ApiResponse<>(200, null, content);
    }

    public static ApiResponse<Map<String, String>> createError(ApiException exception) {

        return new ApiResponse<>(exception.getStatus().value(), exception.getMessage(), null);
    }
}
