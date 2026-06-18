package com.example.furniture_backend.ImplService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.furniture_backend.entities.Product;
import com.example.furniture_backend.exception.ResourceNotFoundException;
import com.example.furniture_backend.repositories.ProductRepository;
import com.example.furniture_backend.requestDto.ProductRequestDto;
import com.example.furniture_backend.responseDto.ProductResponseDto;
import com.example.furniture_backend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final ModelMapper modelmapper;
	
	ProductServiceImpl(ProductRepository productRepository , 
			ModelMapper modelmapper){
		this.productRepository = productRepository;
		this.modelmapper = modelmapper;
	}
	
	@Override
	public ProductResponseDto addProduct(ProductRequestDto dto) {
		
		Product product = modelmapper.map(dto, Product.class);
		
		product.setCreatedAt(LocalDateTime.now());
		
		Product save = productRepository.save(product);
		
		return modelmapper.map(save, ProductResponseDto.class);
	}

	@Override
	public ProductResponseDto getProduct(Long id) {
		
		Product existingProduct = productRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		
		return modelmapper.map(existingProduct, ProductResponseDto.class);
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		
		List<Product> all = productRepository.findAll();
		
		return all.stream()
				.map(p -> modelmapper.map(p, ProductResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
		
		Product existingProduct = productRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		existingProduct.setTitle(dto.getTitle());
		existingProduct.setPrice(dto.getPrice());
		existingProduct.setStock(dto.getStock());
		existingProduct.setCategory(dto.getCategory());
		existingProduct.setDescription(dto.getDescription());
		existingProduct.setImageUrl(dto.getImageUrl());
		
		Product savedProduct = productRepository.save(existingProduct);
		
		return modelmapper.map(savedProduct, ProductResponseDto.class);
	}

	@Override
	public void deleteProduct(Long id) {
	
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		
		productRepository.delete(existingProduct);
		
	}

}
