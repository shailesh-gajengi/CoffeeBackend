package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.dto.FavouriteRequestDTO;
import com.example.CoffeeBackend.dto.FavouriteResponseDTO;
import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.entity.Favourite;
import com.example.CoffeeBackend.mapper.FavouriteMapper;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import com.example.CoffeeBackend.repository.FavouriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final CoffeeRepository coffeeRepository;

    public FavouriteService(
            FavouriteRepository favouriteRepository,
            CoffeeRepository coffeeRepository
    ) {
        this.favouriteRepository = favouriteRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public FavouriteResponseDTO addFavourite(FavouriteRequestDTO request) {

        Favourite existing = favouriteRepository
                .findByUserIdAndCoffeeId(
                        request.getUserId(),
                        request.getCoffeeId()
                )
                .orElse(null);

        if (existing != null) {
            return buildFavouriteResponse(existing);
        }

        Favourite saved = favouriteRepository.save(
                FavouriteMapper.toEntity(request)
        );

        return buildFavouriteResponse(saved);
    }

    public List<FavouriteResponseDTO> getFavouriteByUser(String userId) {

        return favouriteRepository.findByUserId(userId)
                .stream()
                .map(this::buildFavouriteResponse)
                .toList();
    }

    public void removeFavourite(Long id) {

        favouriteRepository.deleteById(id);
    }

    private FavouriteResponseDTO buildFavouriteResponse(Favourite favourite) {

        Coffee coffee = coffeeRepository.findById(favourite.getCoffeeId())
                .orElseThrow(() ->
                        new RuntimeException("Coffee Not Found"));

        return new FavouriteResponseDTO(
                favourite.getId(),
                favourite.getUserId(),
                favourite.getCoffeeId(),
                coffee.getName(),
                coffee.getPrice(),
                coffee.getImageUrl()
        );
    }
}