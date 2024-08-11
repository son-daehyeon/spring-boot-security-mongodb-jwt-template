package com.github.ioloolo.template.domain.api.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
