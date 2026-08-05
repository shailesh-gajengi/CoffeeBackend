package com.example.CoffeeBackend.dto;

public class CartResponseDTO {

    private Long id;
    private Long userId;
    private Long coffeeId;
    private String coffeeName;
    private Double price;
    private String imageUrl;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public CartResponseDTO() {
    }

    public CartResponseDTO(Long id,
                           Long userId,
                           Long coffeeId,
                           String coffeeName,
                           Double price,
                           String imageUrl,
                           Integer quantity) {

        this.id = id;
        this.userId = userId;
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    // Generate getters and setters for all fields
}