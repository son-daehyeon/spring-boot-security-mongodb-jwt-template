package com.github.son_daehyeon.template.common.security.authentication;

import com.github.son_daehyeon.template.domain.auth.exception.AuthenticationFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        UserAuthentication userAuthentication = (UserAuthentication) authentication;

        if (userAuthentication.getPrincipal() == null || userAuthentication.getCredentials() == null) {
            throw new AuthenticationFailException();
        }

        if (!passwordEncoder.matches(userAuthentication.getCredentials(), userAuthentication.getPrincipal().password())) {
            throw new AuthenticationFailException();
        }

        userAuthentication.setAuthenticated(true);

        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthentication.class);
    }
}
