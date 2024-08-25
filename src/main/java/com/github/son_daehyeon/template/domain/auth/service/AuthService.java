package com.github.son_daehyeon.template.domain.auth.service;

import com.github.son_daehyeon.template.common.security.authentication.UserAuthentication;
import com.github.son_daehyeon.template.common.security.jwt.JwtUtil;
import com.github.son_daehyeon.template.domain.auth.dto.request.LoginRequest;
import com.github.son_daehyeon.template.domain.auth.dto.request.RefreshTokenRequest;
import com.github.son_daehyeon.template.domain.auth.dto.request.RegisterRequest;
import com.github.son_daehyeon.template.domain.auth.dto.response.LoginResponse;
import com.github.son_daehyeon.template.domain.auth.exception.AlreadyRegisteredEmailException;
import com.github.son_daehyeon.template.domain.auth.exception.AuthenticationFailException;
import com.github.son_daehyeon.template.domain.auth.exception.InvalidRefreshTokenException;
import com.github.son_daehyeon.template.domain.auth.repository.RefreshTokenRepository;
import com.github.son_daehyeon.template.domain.user.repository.UserRepository;
import com.github.son_daehyeon.template.domain.user.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest dto) {

        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email).orElseThrow(AuthenticationFailException::new);

        authenticationManager.authenticate(new UserAuthentication(user, password));

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Void register(RegisterRequest dto) {

        String email = dto.email();
        String password = dto.password();

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyRegisteredEmailException();
        }

        User user = User.builder()
                .email(email)
                .password(encoder.encode(password))
                .role(User.Role.USER)
                .build();

        userRepository.save(user);

        return null;
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {

        String refreshToken = request.refreshToken();

        String id = refreshTokenRepository.get(refreshToken).orElseThrow(InvalidRefreshTokenException::new);
        refreshTokenRepository.delete(refreshToken);

        User user = userRepository.findById(id).orElseThrow(AuthenticationFailException::new);

        String accessToken = jwtUtil.generateAccessToken(user);
        String newRefreshToken = jwtUtil.generateRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
