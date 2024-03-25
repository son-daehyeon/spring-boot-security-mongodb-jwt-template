package com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.response;

import com.github.ioloolo.template.jwt_auth.common.payload.response.Response;

import lombok.Data;

public class TokenResponse extends Response<TokenResponse.TokenDto> {

	public TokenResponse(String token) {
		super(TokenDto.of("Bearer " + token));
	}

	@Data(staticConstructor = "of")
	public static class TokenDto {

		private final String token;
	}
}
