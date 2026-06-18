package com.example.furniture_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furniture_backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
		Optional<User> findByEmail(String email);
	
}
