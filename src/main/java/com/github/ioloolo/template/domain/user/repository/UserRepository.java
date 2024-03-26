package com.github.ioloolo.template.domain.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.ioloolo.template.domain.user.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
