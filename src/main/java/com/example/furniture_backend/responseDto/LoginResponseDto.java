package com.example.furniture_backend.responseDto;


import lombok.Data;

@Data
public class LoginResponseDto {
	 private Long userId;
	    private String email;
	    private String token;
	    private String role;
}
