package com.github.ioloolo.template.jwt_auth.common;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
public class ExceptionFactory {

	public Exception of(Type type) {
		String message = type == null ? "" : type.getMessage();

		return new Exception(message);
	}

	@Getter
	@RequiredArgsConstructor
	public enum Type {
		USER_NOT_FOUND("존재하지 않는 사용자입니다."),

		ALREADY_EXIST_USERNAME("이미 가입된 아이디가 존재합니다."),
		ALREADY_EXIST_EMAIL("이미 가입된 이메일이 존재합니다."),
		;

		private final String message;
	}
}
