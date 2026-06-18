package com.example.furniture_backend.requestDto;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String email;
	private String password;
}
