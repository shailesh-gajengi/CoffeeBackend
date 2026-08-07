package com.example.CoffeeBackend.controller;

import com.example.CoffeeBackend.dto.FavouriteRequestDTO;
import com.example.CoffeeBackend.dto.FavouriteResponseDTO;
import com.example.CoffeeBackend.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping
    public ResponseEntity<FavouriteResponseDTO> addFavourite(
            @RequestBody FavouriteRequestDTO request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(favouriteService.addFavourite(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavouriteResponseDTO>> getFavourite(
            @PathVariable String userId
    ) {

        return ResponseEntity.ok(
                favouriteService.getFavouriteByUser(userId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFavourite(
            @PathVariable Long id
    ) {

        favouriteService.removeFavourite(id);

        return ResponseEntity.ok("Favourite Removed Successfully");
    }
}