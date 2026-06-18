package com.example.furniture_backend.requestDto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {
	private Long userId;
	private List<OrderItemRequestDto> items;
	private Double totalAmount;
}
