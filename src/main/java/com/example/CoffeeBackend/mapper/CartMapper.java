package com.example.CoffeeBackend.mapper;

import com.example.CoffeeBackend.dto.CartRequestDTO;
import com.example.CoffeeBackend.dto.CartResponseDTO;
import com.example.CoffeeBackend.entity.Cart;

public class CartMapper {

    public static Cart toEntity(CartRequestDTO dto) {

        Cart cart = new Cart();

        cart.setUserId(dto.getUserId());
        cart.setCoffeeId(dto.getCoffeeId());
        cart.setQuantity(dto.getQuantity());

        return cart;
    }


}