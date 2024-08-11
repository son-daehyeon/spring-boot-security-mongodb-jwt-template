package com.github.ioloolo.template.common.exception;

import com.github.ioloolo.template.domain.api.exception.ApiException;

public class AuthenticationFailException extends ApiException {

    public AuthenticationFailException() {

        super("유저를 찾을 수 없거나 비밀번호가 일치하지 않습니다.");
    }
}
