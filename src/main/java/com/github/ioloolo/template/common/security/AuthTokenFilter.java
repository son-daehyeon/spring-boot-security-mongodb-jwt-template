package com.github.ioloolo.template.common.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.ioloolo.template.domain.user.entity.User;
import com.github.ioloolo.template.domain.user.repository.UserRepository;

import jakarta.annotation.Nonnull;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final InnerUserService innerUserService;
	private final UserRepository repository;

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request,
		@Nonnull HttpServletResponse response,
		@Nonnull FilterChain filterChain) throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");
		String accessToken = authorization != null && authorization.startsWith("Bearer ") ? authorization.substring(7) : null;

		try {
			if (jwtUtil.validate(accessToken)) {
				String username = jwtUtil.getUsernameFromAccessToken(accessToken);
				verifyAndSaveAuthentication(request, username);
			}
		} catch (TokenExpiredException e) {
			reissueToken(request, response);
		}

		filterChain.doFilter(request, response);
	}

	private void reissueToken(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) {

		Optional<Cookie> refreshTokenOptional = Arrays.stream(request.getCookies())
				.filter(x -> x.getName().equals("refresh_token"))
				.findAny();

		if (refreshTokenOptional.isEmpty()) return;

		String refreshToken = refreshTokenOptional.get().getValue();
		String userId = redisTemplate.opsForValue().getAndDelete(refreshToken);

		if (userId == null) return;

		User user = repository.findById(userId).orElseThrow();

		Authentication authentication = verifyAndSaveAuthentication(request, user.getUsername());

		response.addHeader("App-Reissue-Token", "1");
		response.addHeader("App-New-Access-Token", jwtUtil.generateAccessToken(authentication));
		response.addHeader("App-New-Refresh-Token", jwtUtil.generateRefreshToken(authentication));
	}

	private Authentication verifyAndSaveAuthentication(@Nonnull HttpServletRequest request, String username) {

		UserDetails userDetails = innerUserService.loadUserByUsername(username);
		AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return authentication;
	}

	public Filter getFilter() {

		return this;
	}
}
