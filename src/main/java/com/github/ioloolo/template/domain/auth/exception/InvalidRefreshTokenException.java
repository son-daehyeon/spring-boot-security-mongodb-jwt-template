package com.github.ioloolo.template.domain.auth.exception;

import com.github.ioloolo.template.common.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends ApiException {

    public InvalidRefreshTokenException() {

        super(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다.");
    }
}
