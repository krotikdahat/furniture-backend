package com.example.furniture_backend.responseDto;

import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String role;
}
