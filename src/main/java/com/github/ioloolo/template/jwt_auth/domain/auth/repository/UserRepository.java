package com.github.ioloolo.template.jwt_auth.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.ioloolo.template.jwt_auth.domain.auth.data.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
