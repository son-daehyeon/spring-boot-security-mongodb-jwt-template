package com.github.ioloolo.template.domain.user.schema;

import com.github.ioloolo.template.domain.user.constant.Role;
import org.springframework.data.annotation.Id;

public record User(
	@Id
	String id,

	String email,

	String password,

	Role role
) {}
