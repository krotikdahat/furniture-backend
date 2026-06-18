package com.example.furniture_backend.requestDto;

import lombok.Data;

@Data
public class UserRequestDto {
	private String name;
	private String email;
	private String password;
	private String phone;
}
