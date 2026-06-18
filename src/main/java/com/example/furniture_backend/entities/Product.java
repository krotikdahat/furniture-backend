package com.example.furniture_backend.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , unique = true)
	private String title;

	@Column(length = 1000)
	private String description;
	
	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Integer stock;

	private String imageUrl;

	private String category;

	private LocalDateTime createdAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;
}
