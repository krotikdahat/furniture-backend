package com.example.furniture_backend.service;

import java.util.List;

import com.example.furniture_backend.requestDto.UserRequestDto;
import com.example.furniture_backend.responseDto.LoginResponseDto;
import com.example.furniture_backend.responseDto.UserResponseDto;

public interface UserService {
	
	public UserResponseDto registerUser(UserRequestDto dto);
	
	public LoginResponseDto loginUser(UserRequestDto dto);
	
	public UserResponseDto getUserById(Long id);
	
	public List<UserResponseDto> getAllUsers();
	
	
}
