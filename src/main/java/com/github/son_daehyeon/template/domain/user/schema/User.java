package com.github.son_daehyeon.template.domain.user.schema;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.HashSet;

@Builder
public record User(
        @Id
        String id,

        String email,
        String password,

        Role role
) {
    public enum Role {
        USER,
        ADMIN(Role.USER),
        ;

        private final Role[] inheritedRoles;

        Role(Role... inheritedRoles) {
            this.inheritedRoles = inheritedRoles;
        }

        public Collection<String> getAuthorization() {

            HashSet<Role> authorization = new HashSet<>();

            collectAuthorization(this, authorization);

            return authorization.stream().map(role -> "ROLE_" + role.name()).toList();
        }

        private void collectAuthorization(Role role, HashSet<Role> roles) {

            roles.add(role);

            for (Role inheritedRole : role.inheritedRoles) {
                collectAuthorization(inheritedRole, roles);
            }
        }
    }
}
