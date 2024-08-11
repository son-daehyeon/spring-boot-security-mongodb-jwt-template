package com.github.ioloolo.template.common.exception;

import com.github.ioloolo.template.domain.api.exception.ApiException;

public class InvalidRefreshTokenException extends ApiException {

    public InvalidRefreshTokenException() {

        super("유효하지 않은 리프레시 토큰입니다.");
    }
}
