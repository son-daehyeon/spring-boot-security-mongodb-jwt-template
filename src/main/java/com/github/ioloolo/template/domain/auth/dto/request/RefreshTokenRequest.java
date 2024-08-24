package com.github.ioloolo.template.domain.auth.dto.request;

import com.github.ioloolo.template.common.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
        String refreshToken
) {}
