package com.example.CoffeeBackend.mapper;

import com.example.CoffeeBackend.dto.OrderItemResponseDTO;
import com.example.CoffeeBackend.dto.OrderResponseDTO;
import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.entity.Order;
import com.example.CoffeeBackend.entity.OrderItem;

import java.util.List;

public class OrderMapper {

    public static Order toEntity(Long userId,
                                 Double totalAmount,
                                 String paymentMethod) {

        Order order = new Order();

        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setPaymentMethod(paymentMethod);

        return order;
    }

    public static OrderItemResponseDTO toOrderItemResponse(
            OrderItem orderItem,
            Coffee coffee
    ) {

        return new OrderItemResponseDTO(
                orderItem.getCoffeeId(),
                coffee.getName(),
                orderItem.getPrice(),
                orderItem.getQuantity()
        );
    }

    public static OrderResponseDTO toOrderResponse(
            Order order,
            List<OrderItemResponseDTO> items
    ) {

        return new OrderResponseDTO(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                items
        );
    }

}