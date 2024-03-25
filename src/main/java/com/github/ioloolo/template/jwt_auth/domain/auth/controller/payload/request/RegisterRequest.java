package com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterRequest {

	@NotBlank
	@Size(min = 6, max = 24)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 32)
	private String password;
}
