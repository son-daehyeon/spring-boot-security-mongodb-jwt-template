package com.github.son_daehyeon.template.domain.auth.dto.request;

import com.github.son_daehyeon.template.common.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(groups = ValidationGroups.NotEmptyGroup.class)
        String refreshToken
) {}
