package com.example.furniture_backend.exception;

public class ResourceNotFoundException extends RuntimeException {
			public ResourceNotFoundException(String message) {
				super(message);
			}
}
