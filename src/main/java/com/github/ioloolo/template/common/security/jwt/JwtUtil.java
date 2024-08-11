package com.github.ioloolo.template.common.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.ioloolo.template.domain.user.repository.RefreshTokenRepository;
import com.github.ioloolo.template.domain.user.schema.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	@Value("${app.security.access-token-expirations-hour}")
	private long accessTokenExpirationsHour;

	@Value("${app.security.refresh-token-expirations-hour}")
	private long refreshTokenExpirationsHour;

	private final RefreshTokenRepository refreshTokenRepository;

	@Value("${app.security.jwt-secret-key}")
	private String secretKey;
	private Algorithm algorithm;

	@PostConstruct
	public void init() {
		algorithm = Algorithm.HMAC256(secretKey);
	}

	public String generateAccessToken(User user) {

		return JWT.create()
				.withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plus(accessTokenExpirationsHour, ChronoUnit.HOURS))
				.withClaim("id", user.id())
				.sign(algorithm);
	}

	public String generateRefreshToken(User user) {

		String token = JWT.create()
				.withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plus(refreshTokenExpirationsHour, ChronoUnit.HOURS))
				.sign(algorithm);

		refreshTokenRepository.setTtl(token, user.id(), refreshTokenExpirationsHour, TimeUnit.HOURS);

		return token;
	}

	public String extractToken(String token) {

		return JWT.require(algorithm)
			.build()
			.verify(token)
			.getClaim("id")
			.asString();
	}

	public boolean validateToken(String token) throws TokenExpiredException {

		if (token == null) return false;

		JWT.require(algorithm).build().verify(token);

		return true;
    }
}
