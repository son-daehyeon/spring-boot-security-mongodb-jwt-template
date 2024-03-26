package com.github.ioloolo.template.domain.user.controller.payload.response;

import com.github.ioloolo.template.common.payload.response.Response;

import lombok.Data;

public class TokenResponse extends Response<TokenResponse.TokenDto> {

	public TokenResponse(TokenDto tokenDto) {

		super(TokenDto.of(tokenDto.getAccessToken(), tokenDto.getRefreshToken()));
	}

	@Data(staticConstructor = "of")
	public static class TokenDto {

		private final String accessToken;
		private final String refreshToken;
	}
}
