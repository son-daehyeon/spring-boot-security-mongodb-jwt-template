package com.github.ioloolo.onlinejudge.domain.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.ioloolo.onlinejudge.config.security.jwt.JwtUtil;
import com.github.ioloolo.onlinejudge.domain.auth.exception.AlreadyTakenEmailException;
import com.github.ioloolo.onlinejudge.domain.auth.exception.AlreadyTakenUsernameException;
import com.github.ioloolo.onlinejudge.domain.auth.model.Role;
import com.github.ioloolo.onlinejudge.domain.auth.model.User;
import com.github.ioloolo.onlinejudge.domain.auth.repository.RoleRepository;
import com.github.ioloolo.onlinejudge.domain.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;

	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtUtil.generateJwtToken(authentication);
	}

	public void register(String username, String email, String password)
			throws AlreadyTakenUsernameException, AlreadyTakenEmailException {

		if (userRepository.existsByUsername(username)) {
			throw new AlreadyTakenUsernameException();
		}

		if (userRepository.existsByEmail(email)) {
			throw new AlreadyTakenEmailException();
		}

		Role userRole = roleRepository.findByName(Role.Roles.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		User user = User.builder()
				.username(username)
				.email(email)
				.password(encoder.encode(password))
				.role(userRole)
				.build();

		userRepository.save(user);
	}
}
