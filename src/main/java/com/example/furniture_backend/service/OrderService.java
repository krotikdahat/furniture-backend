package com.example.furniture_backend.service;

import java.util.List;

import com.example.furniture_backend.eNum.OrderStatus;
import com.example.furniture_backend.requestDto.OrderRequestDto;
import com.example.furniture_backend.responseDto.OrderResponseDto;

public interface OrderService {
	
	public OrderResponseDto placeOrder(OrderRequestDto dto);
	
	public OrderResponseDto getOrderById(Long id);
	
	public List<OrderResponseDto> getAllOrders();
	
	public List<OrderResponseDto> getOrdersByUser(Long userId);
	
	public OrderResponseDto updateOrderStatus(Long id, OrderStatus orderStatus);
	
	public OrderResponseDto cancelOrder(Long id);
}
