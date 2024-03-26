package com.github.ioloolo.template.common.security;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.ioloolo.template.domain.user.entity.User;

@Component
public class JwtUtil {

	private final Algorithm algorithm;

	private final RedisTemplate<String, String> redisTemplate;

	@Value("${app.security.access-token-expirations-hour}")
	private long accessTokenExpirationsHour;

	@Value("${app.security.refresh-token-expirations-hour}")
	private long refreshTokenExpirationsHour;

	public JwtUtil(@Value("${app.security.jwt-secret-key}") String secretKey, RedisTemplate<String, String> redisTemplate) {
		this.algorithm = Algorithm.HMAC256(secretKey);
		this.redisTemplate = redisTemplate;
	}

	public String generateAccessToken(Authentication authentication) {

		User userPrincipal = (User) authentication.getPrincipal();

		return JWT.create()
			.withIssuedAt(Instant.now())
			.withExpiresAt(Instant.now().plus(accessTokenExpirationsHour, TimeUnit.HOURS.toChronoUnit()))
			.withClaim("username", userPrincipal.getUsername())
			.sign(algorithm);
	}

	public String generateRefreshToken(Authentication authentication) {

		User userPrincipal = (User) authentication.getPrincipal();

		String token = JWT.create()
				.withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plus(refreshTokenExpirationsHour, TimeUnit.HOURS.toChronoUnit()))
				.sign(algorithm);

		redisTemplate.opsForValue().set(userPrincipal.getId(), token, refreshTokenExpirationsHour, TimeUnit.HOURS);

		return token;
	}

	public String getUsernameFromAccessToken(String token) {

		return JWT.require(algorithm)
			.build()
			.verify(token)
			.getClaim("username")
			.asString();
	}

	public boolean validate(String token) {

		if (token == null) return false;

		try {
			JWT.require(algorithm).build().verify(token);

			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}
}
