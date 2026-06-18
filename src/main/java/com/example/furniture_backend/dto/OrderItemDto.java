package com.example.furniture_backend.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	private String productName;
    private Double price;
    private Integer quantity;
}
