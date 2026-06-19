package com.example.furniture_backend.ImplService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.furniture_backend.eNum.OrderStatus;
import com.example.furniture_backend.entities.Order;
import com.example.furniture_backend.entities.OrderItem;
import com.example.furniture_backend.entities.Product;
import com.example.furniture_backend.entities.User;
import com.example.furniture_backend.exception.ResourceNotFoundException;
import com.example.furniture_backend.repositories.OrderItemRepository;
import com.example.furniture_backend.repositories.OrderRepository;
import com.example.furniture_backend.repositories.ProductRepository;
import com.example.furniture_backend.repositories.UserRepository;
import com.example.furniture_backend.requestDto.OrderItemRequestDto;
import com.example.furniture_backend.requestDto.OrderRequestDto;
import com.example.furniture_backend.responseDto.OrderResponseDto;
import com.example.furniture_backend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final ModelMapper modelMapper;
	
	public OrderServiceImpl(
	        OrderRepository orderRepository,
	        UserRepository userRepository,
	        ProductRepository productRepository,
	        OrderItemRepository orderItemRepository,
	        ModelMapper modelMapper) {

	    this.orderRepository = orderRepository;
	    this.userRepository = userRepository;
	    this.productRepository = productRepository;
	    this.orderItemRepository = orderItemRepository;
	    this.modelMapper = modelMapper;
	}
	

	@Override
	public OrderResponseDto placeOrder(OrderRequestDto dto) {
		
		 System.out.println("===== PLACE ORDER CALLED =====");
		    System.out.println("UserId = " + dto.getUserId());
		    System.out.println("Items = " + dto.getItems());

	    // FETCH USER
	    User user = userRepository.findById(dto.getUserId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found"));

	    // CREATE ORDER
	    Order order = new Order();
	    order.setUser(user);
	    order.setOrderDate(LocalDateTime.now());
	    order.setOrderStatus(OrderStatus.PLACED);

	    double totalAmount = 0;

	    List<OrderItem> orderItems = new ArrayList<>();

	    for (OrderItemRequestDto itemDto : dto.getItems()) {
	    	
	    	System.out.println("PRODUCT ID: " + itemDto.getProductId());

	    	 Product product = productRepository.findById(itemDto.getProductId())
	    	            .orElseThrow(() ->
	    	                    new RuntimeException("❌ Product not found with ID: " + itemDto.getProductId()));

	    	    System.out.println("FOUND PRODUCT: " + product.getTitle());
	    	    
	    	    System.out.println("ITEM RECEIVED: " + itemDto.getProductId());
	    	    
	    	    System.out.println("PRODUCT EXISTS CHECK: " + productRepository.existsById(itemDto.getProductId()));

	        OrderItem orderItem = new OrderItem();

	        orderItem.setProduct(product);
	        orderItem.setQuantity(itemDto.getQuantity());

	        // unit price (better practice)
	        orderItem.setPrice(product.getPrice());

	        double subTotal = product.getPrice() * itemDto.getQuantity();
	        totalAmount += subTotal;

	        orderItem.setOrder(order);

	        orderItems.add(orderItem);
	    }

	    order.setTotalAmount(totalAmount);
	    order.setOrderItems(orderItems);

	    // 🔥 IMPORTANT: USE CASCADE = ALL in Order entity
	    Order savedOrder = orderRepository.save(order);

	    return modelMapper.map(savedOrder, OrderResponseDto.class);
	}	
	
	
	

	@Override
	public OrderResponseDto getOrderById(Long id) {
		
		Order order = orderRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		
		return modelMapper.map(order,OrderResponseDto.class);
	}

	@Override
	public List<OrderResponseDto> getAllOrders() {
		List<Order> all = orderRepository.findAll();
		return all.stream()
				.map(o -> modelMapper.map(o, OrderResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderResponseDto> getOrdersByUser(Long userId) {
		
		User user = userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		List<Order> orders = orderRepository.findByUser(user);
		
		return orders.stream()
				.map(u -> modelMapper.map(u, OrderResponseDto.class))
				.collect(Collectors.toList());
	}
	
	
	
	@Override
	public OrderResponseDto updateOrderStatus(Long id, OrderStatus status) {

	    // 1. Fetch order
	    Order order = orderRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Order not found with id: " + id));

	    // 2. Update status
	    order.setOrderStatus(status);

	    // 3. Save updated order
	    Order updatedOrder = orderRepository.save(order);

	    // 4. Convert to DTO
	    return modelMapper.map(updatedOrder, OrderResponseDto.class);
	}
	
	@Override
	public OrderResponseDto cancelOrder(Long id) {

	    // 1. Fetch order
	    Order order = orderRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Order not found with id: " + id));

	    // 2. Business rule check (optional but important)
	    if (order.getOrderStatus() == OrderStatus.DELIVERED) {
	        throw new RuntimeException("Cannot cancel delivered order");
	    }

	    // 3. Set status to CANCELLED
	    order.setOrderStatus(OrderStatus.CANCELLED);

	    // 4. Save updated order
	    Order updatedOrder = orderRepository.save(order);

	    // 5. Convert to DTO
	    return modelMapper.map(updatedOrder, OrderResponseDto.class);
	}
}
