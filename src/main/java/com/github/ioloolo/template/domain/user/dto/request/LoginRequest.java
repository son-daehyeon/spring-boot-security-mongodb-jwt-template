package com.github.ioloolo.template.domain.user.dto.request;

import com.github.ioloolo.template.common.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
		String email,

		@NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
		String password
) {}
