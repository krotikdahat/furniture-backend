package com.example.furniture_backend.service;

import java.util.List;

import com.example.furniture_backend.requestDto.ProductRequestDto;
import com.example.furniture_backend.responseDto.ProductResponseDto;

public interface ProductService {
	
	public ProductResponseDto addProduct(ProductRequestDto dto);
	
	public ProductResponseDto getProduct(Long id);
	
	List<ProductResponseDto> getAllProducts();
	
	public ProductResponseDto updateProduct(Long id , ProductRequestDto dto);
	
	public void deleteProduct(Long id);
}
