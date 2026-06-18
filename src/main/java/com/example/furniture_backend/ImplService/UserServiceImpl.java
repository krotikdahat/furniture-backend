package com.example.furniture_backend.ImplService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.furniture_backend.entities.User;
import com.example.furniture_backend.exception.EmailAlreadyExistsException;
import com.example.furniture_backend.exception.InvalidCredentialsException;
import com.example.furniture_backend.exception.ResourceNotFoundException;
import com.example.furniture_backend.repositories.UserRepository;
import com.example.furniture_backend.requestDto.UserRequestDto;
import com.example.furniture_backend.responseDto.LoginResponseDto;
import com.example.furniture_backend.responseDto.UserResponseDto;
import com.example.furniture_backend.security.JwtService;
import com.example.furniture_backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;    
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {

        Optional<User> existingUser =
                userRepository.findByEmail(dto.getEmail());

        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = modelMapper.map(dto, User.class);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public LoginResponseDto loginUser(UserRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        LoginResponseDto response = new LoginResponseDto();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setToken(token);
        response.setRole(user.getRole());
        
        System.out.println("USER OBJECT: " + user);
        System.out.println("ROLE: " + user.getRole());
        System.out.println("LOGIN USER ID: " + user.getId());
        
        return response;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> all = userRepository.findAll();

        return all.stream()
                .map(u -> modelMapper.map(u, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return modelMapper.map(user, UserResponseDto.class);
    }
}