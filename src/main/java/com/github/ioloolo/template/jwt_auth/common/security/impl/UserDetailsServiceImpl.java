package com.github.ioloolo.template.jwt_auth.common.security.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ioloolo.template.jwt_auth.common.ExceptionFactory;
import com.github.ioloolo.template.jwt_auth.domain.auth.data.User;
import com.github.ioloolo.template.jwt_auth.domain.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	private final ExceptionFactory exceptionFactory;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException(
				exceptionFactory.of(ExceptionFactory.Type.USER_NOT_FOUND).getMessage()));

		return UserDetailsImpl.from(user);
	}
}
