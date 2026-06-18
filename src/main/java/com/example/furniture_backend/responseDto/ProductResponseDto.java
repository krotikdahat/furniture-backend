package com.example.furniture_backend.responseDto;

import lombok.Data;

@Data
public class ProductResponseDto {
	private Long id;
	private String title;
	private String description;
	private Double price;
	private Integer stock;
	private String imageUrl;
	private String category;
}
