package com.github.son_daehyeon.template.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken
) {}
