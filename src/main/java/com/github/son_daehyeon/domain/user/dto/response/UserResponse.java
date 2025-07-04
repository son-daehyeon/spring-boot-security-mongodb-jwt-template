package com.github.son_daehyeon.domain.user.dto.response;

import com.github.son_daehyeon.domain.user.schema.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserResponse {

    String id;
    String email;
    User.Role role;

    public static UserResponse from(User user) {

        return UserResponse.of(user.getId(), user.getEmail(), user.getRole());
    }
}
