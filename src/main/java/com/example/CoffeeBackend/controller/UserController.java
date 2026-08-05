package com.example.CoffeeBackend.controller;

import com.example.CoffeeBackend.dto.LoginRequestDTO;
import com.example.CoffeeBackend.dto.LoginResponseDTO;
import com.example.CoffeeBackend.dto.UserRequestDTO;
import com.example.CoffeeBackend.dto.UserResponseDTO;
import com.example.CoffeeBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(
            @Valid @RequestBody UserRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(userService.loginUser(request));
    }
}