package com.example.furniture_backend.requestDto;

import lombok.Data;

@Data
public class OrderItemRequestDto {
	private Long productId;
	private Integer quantity;
}
