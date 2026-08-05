package com.example.CoffeeBackend.dto;

import java.util.List;

public class OrderResponseDTO {

    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private String paymentMethod;
    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long orderId,
                            Long userId,
                            Double totalAmount,
                            String paymentMethod,
                            List<OrderItemResponseDTO> items) {

        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDTO> items) {
        this.items = items;
    }
}