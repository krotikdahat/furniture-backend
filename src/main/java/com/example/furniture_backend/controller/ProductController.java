package com.example.furniture_backend.controller;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.furniture_backend.requestDto.ProductRequestDto;
import com.example.furniture_backend.responseDto.ProductResponseDto;
import com.example.furniture_backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public ProductResponseDto addProduct(@RequestBody ProductRequestDto dto) {
		return productService.addProduct(dto);
	}
	
	@GetMapping("/{id}")
	public ProductResponseDto getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
	@GetMapping
	public List<ProductResponseDto> getAllProduct() {
		return productService.getAllProducts();
	}
	
	@PutMapping("/{id}")
	public ProductResponseDto updateProduct(@PathVariable Long id , @RequestBody ProductRequestDto dto) {
		return productService.updateProduct(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
}
