package com.example.CoffeeBackend.dto;

public class CartRequestDTO {

    private Long userId;
    private Long coffeeId;
    private Integer quantity;

    public CartRequestDTO() {}

    public CartRequestDTO(Long userId, Long coffeeId, Integer quantity) {
        this.userId = userId;
        this.coffeeId = coffeeId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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