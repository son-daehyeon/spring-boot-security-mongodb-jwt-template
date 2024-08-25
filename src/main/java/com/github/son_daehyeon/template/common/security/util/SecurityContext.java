package com.github.son_daehyeon.template.common.security.util;

import com.github.son_daehyeon.template.common.security.authentication.UserAuthentication;
import com.github.son_daehyeon.template.domain.user.schema.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContext {

    public static User getUser() {
        return ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    }
}
