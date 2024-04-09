package com.github.ioloolo.template.common.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.ioloolo.template.domain.user.entity.User;

@Component
public class JwtUtil {

	@Value("${app.security.access-token-expirations-hour}")
	private long accessTokenExpirationsHour;

	@Value("${app.security.refresh-token-expirations-hour}")
	private long refreshTokenExpirationsHour;

	private final Algorithm algorithm;
	private final RedisTemplate<String, String> redisTemplate;

	public JwtUtil(@Value("${app.security.jwt-secret-key}") String secretKey, RedisTemplate<String, String> redisTemplate) {

		this.algorithm = Algorithm.HMAC256(secretKey);
		this.redisTemplate = redisTemplate;
	}

	public String generateAccessToken(Authentication authentication) {

		User userPrincipal = (User) authentication.getPrincipal();

		return JWT.create()
			.withIssuedAt(Instant.now())
			.withExpiresAt(Instant.now().plus(accessTokenExpirationsHour, ChronoUnit.HOURS))
			.withClaim("username", userPrincipal.getUsername())
			.sign(algorithm);
	}

	public String generateRefreshToken(Authentication authentication) {

		User userPrincipal = (User) authentication.getPrincipal();

		String token = JWT.create()
				.withIssuedAt(Instant.now())
				.withExpiresAt(Instant.now().plus(refreshTokenExpirationsHour, ChronoUnit.SECONDS))
				.sign(algorithm);

		redisTemplate.opsForValue().set(token, userPrincipal.getId(), refreshTokenExpirationsHour, TimeUnit.HOURS);

		return token;
	}

	public String getUsernameFromAccessToken(String token) {

		return JWT.require(algorithm)
			.build()
			.verify(token)
			.getClaim("username")
			.asString();
	}

	public boolean validate(String token) throws TokenExpiredException {

		if (token == null) return false;

		try {
			JWT.require(algorithm).build().verify(token);

			return true;
		} catch (TokenExpiredException e) {
			throw e;
		} catch (JWTVerificationException e) {
			return false;
		}
	}
}
