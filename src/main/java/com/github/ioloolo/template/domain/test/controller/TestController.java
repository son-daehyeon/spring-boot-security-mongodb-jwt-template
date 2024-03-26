package com.github.ioloolo.template.domain.test.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ioloolo.template.common.payload.response.Response;
import com.github.ioloolo.template.common.security.SecurityUtil;
import com.github.ioloolo.template.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

	private final SecurityUtil securityUtil;

	@GetMapping("/all")
	public ResponseEntity<?> allAccess() {
		return ResponseEntity.ok(new Response<>(Map.ofEntries(Map.entry("access", "all"))));
	}

	@GetMapping("/user")
	public ResponseEntity<?> userAccess() {

		User user = securityUtil.getUser();

		return ResponseEntity.ok(new Response<>(Map.ofEntries(Map.entry("access", "user"), Map.entry("user", user.getUsername()))));
	}

	@GetMapping("/admin")
	public ResponseEntity<?> adminAccess() {

		User user = securityUtil.getUser();

		return ResponseEntity.ok(new Response<>(Map.ofEntries(Map.entry("access", "admin"), Map.entry("user", user.getUsername()))));
	}
}
