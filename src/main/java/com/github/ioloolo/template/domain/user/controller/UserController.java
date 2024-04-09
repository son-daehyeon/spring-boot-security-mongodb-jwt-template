package com.github.ioloolo.template.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ioloolo.template.domain.user.controller.payload.request.LoginRequest;
import com.github.ioloolo.template.domain.user.controller.payload.request.RegisterRequest;
import com.github.ioloolo.template.domain.user.controller.payload.response.TokenResponse;
import com.github.ioloolo.template.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService service;

	@PostMapping
	public ResponseEntity<TokenResponse> login(@Validated @RequestBody LoginRequest loginRequest) {

		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		TokenResponse.TokenDto tokenDto = service.login(username, password);

		return ResponseEntity.ok(new TokenResponse(tokenDto));
	}

	@PutMapping
	public ResponseEntity<Void> register(@Validated @RequestBody RegisterRequest signUpRequest) throws Exception {

		String username = signUpRequest.getUsername();
		String email = signUpRequest.getEmail();
		String password = signUpRequest.getPassword();

		service.register(username, email, password);

		return ResponseEntity.ok().build();
	}
}
