package com.example.CoffeeBackend.repository;

import com.example.CoffeeBackend.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    List<Favourite> findByUserId(Long userId);

    Optional<Favourite> findByUserIdAndCoffeeId(Long userId, Long coffeeId);

}