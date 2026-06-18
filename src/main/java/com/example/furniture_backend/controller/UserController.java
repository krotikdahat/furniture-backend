package com.example.furniture_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furniture_backend.requestDto.UserRequestDto;
import com.example.furniture_backend.responseDto.LoginResponseDto;
import com.example.furniture_backend.responseDto.UserResponseDto;
import com.example.furniture_backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto registerUser(
            @RequestBody UserRequestDto dto) {

        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(
            @RequestBody UserRequestDto dto) {

        return userService.loginUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }
}