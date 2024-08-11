package com.github.ioloolo.template.domain.user.dto.request;

import com.github.ioloolo.template.common.validation.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		@NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
		@Email(groups = ValidationGroups.PatternGroup.class)
		String email,

		@NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
		@Size(min = 8, max = 100, groups = ValidationGroups.LengthGroup.class)
		String password
) {}
