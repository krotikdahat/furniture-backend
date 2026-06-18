package com.example.furniture_backend.responseDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponseDto {
	private Long id;
	private Double totalAmount;
	private String orderStatus;
	private LocalDateTime orderDate;
	 private List<OrderItemResponseDto> items;
}
