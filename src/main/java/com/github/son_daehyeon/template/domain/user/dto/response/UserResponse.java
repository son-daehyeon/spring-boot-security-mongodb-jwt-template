package com.github.son_daehyeon.template.domain.user.dto.response;

import com.github.son_daehyeon.template.domain.user.schema.User;

import lombok.Builder;

@Builder
public record UserResponse(

	User user
) {
}
