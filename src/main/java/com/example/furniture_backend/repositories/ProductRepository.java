package com.example.furniture_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furniture_backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
		Optional<Product> findByTitle(String title);
}
