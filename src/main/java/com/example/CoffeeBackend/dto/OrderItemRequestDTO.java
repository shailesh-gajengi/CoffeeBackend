package com.example.CoffeeBackend.dto;

public class OrderItemRequestDTO {

    private Long coffeeId;
    private Integer quantity;

    public OrderItemRequestDTO() {
    }

    public OrderItemRequestDTO(Long coffeeId, Integer quantity) {
        this.coffeeId = coffeeId;
        this.quantity = quantity;
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