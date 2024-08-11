package com.github.ioloolo.template.domain.user.controller;

import com.github.ioloolo.template.common.validation.ValidationGroups;
import com.github.ioloolo.template.domain.api.dto.response.ApiResponse;
import com.github.ioloolo.template.domain.user.dto.request.LoginRequest;
import com.github.ioloolo.template.domain.user.dto.request.RefreshTokenRequest;
import com.github.ioloolo.template.domain.user.dto.request.RegisterRequest;
import com.github.ioloolo.template.domain.user.dto.response.TokenResponse;
import com.github.ioloolo.template.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ApiResponse<TokenResponse> login(@Validated(ValidationGroups.class) @RequestBody LoginRequest request) {

		return ApiResponse.createSuccess(userService.login(request));
	}

	@PostMapping("/register")
	public ApiResponse<Void> register(@Validated(ValidationGroups.class) @RequestBody RegisterRequest request) {

		return ApiResponse.createSuccess(userService.register(request));
	}

	@PostMapping("/refresh-token")
	public ApiResponse<TokenResponse> refreshToken(@Validated(ValidationGroups.class) @RequestBody RefreshTokenRequest request) {

		return ApiResponse.createSuccess(userService.refreshToken(request));
	}
}
