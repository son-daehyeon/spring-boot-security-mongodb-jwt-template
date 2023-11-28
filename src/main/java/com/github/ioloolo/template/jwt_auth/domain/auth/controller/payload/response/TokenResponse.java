package com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.response;

import java.util.Map;

import com.github.ioloolo.template.jwt_auth.common.payload.response.KVResponse;

public class TokenResponse extends KVResponse {

	public TokenResponse(String token) {
		super(Map.of("token", "Bearer " + token));
	}
}
