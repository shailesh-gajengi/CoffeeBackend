package com.example.CoffeeBackend.controller;

import com.example.CoffeeBackend.dto.CartRequestDTO;
import com.example.CoffeeBackend.dto.CartResponseDTO;
import com.example.CoffeeBackend.entity.Cart;
import com.example.CoffeeBackend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestBody CartRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addToCart(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartResponseDTO>> getCart(
            @PathVariable Long userId) {

        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDTO> updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return ResponseEntity.ok(cartService.updateQuantity(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCart(@PathVariable Long id) {

        cartService.removeFromCart(id);

        return ResponseEntity.ok("Cart Item Removed Successfully");
    }
}