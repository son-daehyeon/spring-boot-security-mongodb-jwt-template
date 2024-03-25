package com.github.ioloolo.template.jwt_auth.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.request.LoginRequest;
import com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.request.RegisterRequest;
import com.github.ioloolo.template.jwt_auth.domain.auth.controller.payload.response.TokenResponse;
import com.github.ioloolo.template.jwt_auth.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {

	private final AuthService service;

	@PostMapping
	@PreAuthorize("isAnonymous()")
	public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		String jwtToken = service.login(username, password);

		return ResponseEntity.ok(new TokenResponse(jwtToken));
	}

	@PutMapping
	@PreAuthorize("isAnonymous()")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest signUpRequest) throws Exception {

		String username = signUpRequest.getUsername();
		String email = signUpRequest.getEmail();
		String password = signUpRequest.getPassword();

		service.register(username, email, password);
		String jwtToken = service.login(username, password);

		return ResponseEntity.ok(new TokenResponse(jwtToken));
	}
}
