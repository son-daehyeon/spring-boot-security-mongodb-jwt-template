package com.github.ioloolo.template.common.exception;

import com.github.ioloolo.template.domain.api.exception.ApiException;

public class AccessTokenExpiredException extends ApiException {

    public AccessTokenExpiredException() {

        super("토큰이 만료되었습니다.");
    }
}
