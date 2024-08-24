package com.github.ioloolo.template.common.security.util;

import com.github.ioloolo.template.common.security.authentication.UserAuthentication;
import com.github.ioloolo.template.domain.user.schema.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContext {

    public static User getUser() {
        return ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    }
}
