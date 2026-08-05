package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.dto.LoginRequestDTO;
import com.example.CoffeeBackend.dto.LoginResponseDTO;
import com.example.CoffeeBackend.dto.UserRequestDTO;
import com.example.CoffeeBackend.dto.UserResponseDTO;
import com.example.CoffeeBackend.entity.User;
import com.example.CoffeeBackend.exception.InvalidPasswordException;
import com.example.CoffeeBackend.exception.UserAlreadyExistsException;
import com.example.CoffeeBackend.exception.UserNotFoundException;
import com.example.CoffeeBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerUser(UserRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone()
        );
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
    public LoginResponseDTO loginUser(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }

        return new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                "Login Successful"
        );
    }
}