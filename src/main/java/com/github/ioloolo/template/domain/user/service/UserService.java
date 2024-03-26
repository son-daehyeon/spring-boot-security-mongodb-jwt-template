package com.github.ioloolo.template.domain.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.ioloolo.template.common.security.JwtUtil;
import com.github.ioloolo.template.domain.user.entity.User;
import com.github.ioloolo.template.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;

	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtUtil.from(authentication);
	}

	public void register(String username, String email, String password) throws Exception {

		if (repository.existsByUsername(username)) {
			throw new Exception("해당 아이디로 가입된 계정이 존재합니다.");
		}

		if (repository.existsByEmail(email)) {
			throw new Exception("해당 메일로 가입된 계정이 존재합니다.");
		}

		User user = User.builder()
			.username(username)
			.email(email)
			.password(encoder.encode(password))
			.authority(User.Role.ROLE_USER)
			.build();

		repository.save(user);
	}
}
