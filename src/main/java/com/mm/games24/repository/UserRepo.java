package com.mm.games24.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.games24.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}
