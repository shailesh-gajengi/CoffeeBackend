package com.example.CoffeeBackend.mapper;

import com.example.CoffeeBackend.dto.FavouriteRequestDTO;
import com.example.CoffeeBackend.entity.Favourite;

public class FavouriteMapper {

    public static Favourite toEntity(FavouriteRequestDTO dto) {

        Favourite favourite = new Favourite();

        favourite.setUserId(dto.getUserId());
        favourite.setCoffeeId(dto.getCoffeeId());

        return favourite;
    }
}