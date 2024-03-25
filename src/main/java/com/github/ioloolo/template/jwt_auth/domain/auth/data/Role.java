package com.github.ioloolo.template.jwt_auth.domain.auth.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "roles")
public class Role {

	@Id
	private final String id;

	private final Roles name;

	public enum Roles {
		ROLE_USER,
		ROLE_ADMIN
	}
}
