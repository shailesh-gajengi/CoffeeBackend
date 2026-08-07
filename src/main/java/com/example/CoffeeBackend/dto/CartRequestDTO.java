package com.example.CoffeeBackend.dto;

public class CartRequestDTO {

    private String userId;
    private Long coffeeId;
    private Integer quantity;

    public CartRequestDTO() {}

    public CartRequestDTO(String userId, Long coffeeId, Integer quantity) {
        this.userId = userId;
        this.coffeeId = coffeeId;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}