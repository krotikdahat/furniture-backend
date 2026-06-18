package com.example.furniture_backend.requestDto;

import lombok.Data;

@Data
public class ProductRequestDto {
	private String title;
	private String description;
	private Double price;
	private Integer stock;
	private String imageUrl;
	private String category;
}
