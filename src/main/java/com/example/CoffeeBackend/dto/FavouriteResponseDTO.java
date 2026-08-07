package com.example.CoffeeBackend.dto;

public class FavouriteResponseDTO {

    private Long id;
    private String userId;
    private Long coffeeId;
    private String coffeeName;
    private Double price;
    private String imageUrl;

    public FavouriteResponseDTO() {
    }

    public FavouriteResponseDTO(
            Long id,
            String userId,
            Long coffeeId,
            String coffeeName,
            Double price,
            String imageUrl
    ) {
        this.id = id;
        this.userId = userId;
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}