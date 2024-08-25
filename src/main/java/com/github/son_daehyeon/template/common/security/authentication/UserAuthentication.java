package com.github.son_daehyeon.template.common.security.authentication;

import com.github.son_daehyeon.template.domain.user.schema.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {

    private final User user;
    private final String password;

    @Getter @Setter
    private boolean authenticated = false;

    public UserAuthentication(User user) {
        this.user = user;
        this.password = null;
    }

    public UserAuthentication(User user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.role().getAuthorization().stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getCredentials() {

        return password;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public User getPrincipal() {

        return user;
    }

    @Override
    public String getName() {

        return user.id();
    }
}
