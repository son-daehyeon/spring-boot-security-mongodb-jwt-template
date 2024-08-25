package com.github.son_daehyeon.template.domain.auth.controller;

import com.github.son_daehyeon.template.common.validation.ValidationGroups;
import com.github.son_daehyeon.template.common.api.dto.response.ApiResponse;
import com.github.son_daehyeon.template.domain.auth.dto.request.LoginRequest;
import com.github.son_daehyeon.template.domain.auth.dto.request.RefreshTokenRequest;
import com.github.son_daehyeon.template.domain.auth.dto.request.RegisterRequest;
import com.github.son_daehyeon.template.domain.auth.dto.response.LoginResponse;
import com.github.son_daehyeon.template.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Validated(ValidationGroups.class) @RequestBody LoginRequest request) {

        return ApiResponse.createSuccess(authService.login(request));
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@Validated(ValidationGroups.class) @RequestBody RegisterRequest request) {

        return ApiResponse.createSuccess(authService.register(request));
    }

    @PostMapping("/refresh-token")
    public ApiResponse<LoginResponse> refreshToken(@Validated(ValidationGroups.class) @RequestBody RefreshTokenRequest request) {

        return ApiResponse.createSuccess(authService.refreshToken(request));
    }
}
