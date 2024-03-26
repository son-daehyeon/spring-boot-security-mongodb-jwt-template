package com.github.ioloolo.template.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.github.ioloolo.template.domain.user.entity.User;

@Component
public class SecurityUtil {

	public User getUser() {

		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
