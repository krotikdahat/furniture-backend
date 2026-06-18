package com.example.furniture_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.furniture_backend.entities.Order;
import com.example.furniture_backend.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}