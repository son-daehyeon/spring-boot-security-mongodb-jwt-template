package com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {

	@NotBlank
	private final String username;

	@NotBlank
	private final String password;
}
