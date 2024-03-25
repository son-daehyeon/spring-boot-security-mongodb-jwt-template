package com.github.ioloolo.template.jwt_auth.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.ioloolo.template.jwt_auth.domain.auth.data.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(Role.Roles name);
}
