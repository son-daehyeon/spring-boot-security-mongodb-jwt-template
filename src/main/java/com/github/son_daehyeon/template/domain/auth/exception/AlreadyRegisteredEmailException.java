package com.github.son_daehyeon.template.domain.auth.exception;

import com.github.son_daehyeon.template.common.api.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AlreadyRegisteredEmailException extends ApiException {

    public AlreadyRegisteredEmailException() {
        super(HttpStatus.CONFLICT, "해당 메일로 가입된 계정이 존재합니다.");
    }
}
