package com.example.furniture_backend.responseDto;

import lombok.Data;

@Data
public class OrderItemResponseDto {
	
	private Long productId;

    private String productTitle;

    private Double price;

    private Integer quantity;
}
