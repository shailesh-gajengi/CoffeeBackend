package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.dto.CartRequestDTO;
import com.example.CoffeeBackend.dto.CartResponseDTO;
import com.example.CoffeeBackend.entity.Cart;
import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.exception.CartNotFoundException;
import com.example.CoffeeBackend.mapper.CartMapper;
import com.example.CoffeeBackend.repository.CartRepository;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CoffeeRepository coffeeRepository;

    public CartService(CartRepository cartRepository,
                       CoffeeRepository coffeeRepository) {
        this.cartRepository = cartRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public CartResponseDTO addToCart(CartRequestDTO request) {

        Cart cart = CartMapper.toEntity(request);

        Cart existingCart = cartRepository
                .findByUserIdAndCoffeeId(cart.getUserId(), cart.getCoffeeId())
                .orElse(null);

        Cart saved;

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity() + cart.getQuantity());

            saved = cartRepository.save(existingCart);

        } else {

            saved = cartRepository.save(cart);

        }

        return buildCartResponse(saved);
    }

    public List<CartResponseDTO> getCartByUser(Long userId) {

        return cartRepository.findByUserId(userId)
                .stream()
                .map(this::buildCartResponse)
                .toList();
    }

    public CartResponseDTO updateQuantity(Long id, int quantity) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new CartNotFoundException("Cart Item Not Found"));

        cart.setQuantity(quantity);

        Cart saved = cartRepository.save(cart);

        return buildCartResponse(saved);
    }

    public void removeFromCart(Long id) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new CartNotFoundException("Cart Item Not Found"));

        cartRepository.delete(cart);
    }

    private CartResponseDTO buildCartResponse(Cart cart) {

        Coffee coffee = coffeeRepository.findById(cart.getCoffeeId())
                .orElseThrow(() ->
                        new RuntimeException("Coffee Not Found"));

        return new CartResponseDTO(
                cart.getId(),
                cart.getUserId(),
                cart.getCoffeeId(),
                coffee.getName(),
                coffee.getPrice(),
                coffee.getImageUrl(),
                cart.getQuantity()
        );
    }
}