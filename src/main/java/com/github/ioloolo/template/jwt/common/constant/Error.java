package com.github.ioloolo.template.jwt.common.constant;

import java.util.EnumSet;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {
	ALREADY_TAKEN_USERNAME("이미 사용중인 아이디입니다."),
	ALREADY_TAKEN_EMAIL("이미 사용중인 이메일입니다."),
	;

	private final String message;

	public static Optional<Error> fromClass(Class<?> clazz) {
		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		String output = clazz.getSimpleName()
				.replace("Exception", "")
				.replaceAll(regex, replacement)
				.toUpperCase();

		EnumSet<Error> errors = EnumSet.allOf(Error.class);

		for (Error error : errors)
			if (error.name().equals(output))
				return Optional.of(error);

		return Optional.empty();
	}
}
