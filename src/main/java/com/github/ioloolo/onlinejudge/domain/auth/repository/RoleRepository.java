package com.github.ioloolo.onlinejudge.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.ioloolo.onlinejudge.domain.auth.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

  Optional<Role> findByName(Role.Roles name);
}
