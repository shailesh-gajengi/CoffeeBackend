package com.example.CoffeeBackend.repository;

import com.example.CoffeeBackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(String userId);

    Optional<Cart> findByUserIdAndCoffeeId(String userId, Long coffeeId);
}