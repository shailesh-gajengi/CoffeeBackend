package com.example.CoffeeBackend.dto;

public class OrderItemResponseDTO {

    private Long coffeeId;
    private String coffeeName;
    private Double price;
    private Integer quantity;

    public OrderItemResponseDTO() {
    }

    public OrderItemResponseDTO(Long coffeeId, String coffeeName, Double price, Integer quantity) {
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}