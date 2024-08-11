package com.github.ioloolo.template.domain.user.service;

import com.github.ioloolo.template.common.exception.AuthenticationFailException;
import com.github.ioloolo.template.common.exception.InvalidRefreshTokenException;
import com.github.ioloolo.template.common.security.authentication.UserAuthentication;
import com.github.ioloolo.template.common.security.jwt.JwtUtil;
import com.github.ioloolo.template.domain.api.exception.ApiException;
import com.github.ioloolo.template.domain.user.constant.Role;
import com.github.ioloolo.template.domain.user.dto.request.LoginRequest;
import com.github.ioloolo.template.domain.user.dto.request.RefreshTokenRequest;
import com.github.ioloolo.template.domain.user.dto.request.RegisterRequest;
import com.github.ioloolo.template.domain.user.dto.response.TokenResponse;
import com.github.ioloolo.template.domain.user.repository.RefreshTokenRepository;
import com.github.ioloolo.template.domain.user.repository.UserRepository;
import com.github.ioloolo.template.domain.user.schema.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;

	public TokenResponse login(LoginRequest dto) {

		String email = dto.email();
		String password = dto.password();

		User user = userRepository.findByEmail(email).orElseThrow(AuthenticationFailException::new);

		UserAuthentication userAuthentication = new UserAuthentication(user, password);
		UserAuthentication authentication = (UserAuthentication) authenticationManager.authenticate(userAuthentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = jwtUtil.generateAccessToken(user);
		String refreshToken = jwtUtil.generateRefreshToken(user);

		return new TokenResponse(accessToken, refreshToken);
	}

	public Void register(RegisterRequest dto) {

		String email = dto.email();
		String password = dto.password();

		if (userRepository.existsByEmail(email)) {
			throw new ApiException("해당 메일로 가입된 계정이 존재합니다.");
		}

		User user = new User(null, email, encoder.encode(password), Role.USER);

		userRepository.save(user);

        return null;
    }

	public TokenResponse refreshToken(RefreshTokenRequest request) {

		String refreshToken = request.refreshToken();

		String id = refreshTokenRepository.get(refreshToken).orElseThrow(InvalidRefreshTokenException::new);
		refreshTokenRepository.delete(refreshToken);

		User user = userRepository.findById(id).orElseThrow(AuthenticationFailException::new);

		String accessToken = jwtUtil.generateAccessToken(user);
		String newRefreshToken = jwtUtil.generateRefreshToken(user);

		return new TokenResponse(accessToken, newRefreshToken);
	}
}
