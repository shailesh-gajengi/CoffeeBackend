package com.example.CoffeeBackend.dto;

import java.util.List;

public class OrderRequestDTO {

    private Long userId;
    private String paymentMethod;
    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Long userId, String paymentMethod, List<OrderItemRequestDTO> items) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}