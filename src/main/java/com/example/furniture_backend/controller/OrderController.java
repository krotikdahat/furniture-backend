package com.example.furniture_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.furniture_backend.eNum.OrderStatus;
import com.example.furniture_backend.requestDto.OrderRequestDto;
import com.example.furniture_backend.responseDto.OrderResponseDto;
import com.example.furniture_backend.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
	    this.orderService = orderService;
	}
	
	@PostMapping("/place")
	public OrderResponseDto placeOrder(@RequestBody OrderRequestDto dto) {
		return orderService.placeOrder(dto);
	}
	
	@GetMapping("/{id}")
	public OrderResponseDto getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
	
	@GetMapping
	public List<OrderResponseDto> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/user/{userId}")
	public List<OrderResponseDto> getOrdersByUser(@PathVariable Long userId){
		return orderService.getOrdersByUser(userId);
	}
	
	@PutMapping("/{id}/status")
	public OrderResponseDto updateOrderStatus(
	        @PathVariable Long id,
	        @RequestParam OrderStatus status) {

	    return orderService.updateOrderStatus(id, status);
	}
	
	@PutMapping("/{id}/cancel")
	public OrderResponseDto cancelOrder(@PathVariable Long id) {
	    return orderService.cancelOrder(id);
	}
}
