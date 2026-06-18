package com.example.furniture_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furniture_backend.entities.Order;
import com.example.furniture_backend.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
				List<OrderItem> findByOrder(Order order);
}
