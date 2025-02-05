package com.github.son_daehyeon.template.domain.auth.dto.request;

import com.github.son_daehyeon.template.common.validation.RegExp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RegisterRequest(

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = RegExp.PASSWORD_EXPRESSION, message = RegExp.PASSWORD_MESSAGE)
    String password
) {
}
