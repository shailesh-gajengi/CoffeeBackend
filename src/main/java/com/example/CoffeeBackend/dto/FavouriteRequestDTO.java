package com.example.CoffeeBackend.dto;

public class FavouriteRequestDTO {

    private String userId;
    private Long coffeeId;

    public FavouriteRequestDTO() {
    }

    public FavouriteRequestDTO(String userId, Long coffeeId) {
        this.userId = userId;
        this.coffeeId = coffeeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }
}