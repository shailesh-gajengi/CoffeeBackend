package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.dto.OrderItemRequestDTO;
import com.example.CoffeeBackend.dto.OrderItemResponseDTO;
import com.example.CoffeeBackend.dto.OrderRequestDTO;
import com.example.CoffeeBackend.dto.OrderResponseDTO;
import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.entity.Order;
import com.example.CoffeeBackend.entity.OrderItem;
import com.example.CoffeeBackend.exception.OrderNotFoundException;
import com.example.CoffeeBackend.mapper.OrderMapper;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import com.example.CoffeeBackend.repository.OrderItemRepository;
import com.example.CoffeeBackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CoffeeRepository coffeeRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CoffeeRepository coffeeRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public OrderResponseDTO placeOrder(OrderRequestDTO request) {

        double totalAmount = 0;

        for (OrderItemRequestDTO item : request.getItems()) {

            Coffee coffee = coffeeRepository.findById(item.getCoffeeId())
                    .orElseThrow(() ->
                            new RuntimeException("Coffee Not Found"));

            totalAmount += coffee.getPrice() * item.getQuantity();
        }

        Order order = OrderMapper.toEntity(
                request.getUserId(),
                totalAmount,
                request.getPaymentMethod()
        );

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequestDTO item : request.getItems()) {

            Coffee coffee = coffeeRepository.findById(item.getCoffeeId())
                    .orElseThrow(() ->
                            new RuntimeException("Coffee Not Found"));

            OrderItem orderItem = new OrderItem();

            orderItem.setOrderId(savedOrder.getId());
            orderItem.setCoffeeId(coffee.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(coffee.getPrice());

            orderItemRepository.save(orderItem);
        }

        return buildOrderResponse(savedOrder);
    }

    public List<OrderResponseDTO> getOrdersByUser(String userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::buildOrderResponse)
                .toList();
    }

    private OrderResponseDTO buildOrderResponse(Order order) {

        List<OrderItem> orderItems =
                orderItemRepository.findByOrderId(order.getId());

        List<OrderItemResponseDTO> items = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {

            Coffee coffee = coffeeRepository.findById(orderItem.getCoffeeId())
                    .orElseThrow(() ->
                            new RuntimeException("Coffee Not Found"));

            items.add(
                    OrderMapper.toOrderItemResponse(
                            orderItem,
                            coffee
                    )
            );
        }

        return OrderMapper.toOrderResponse(order, items);
    }

    public OrderResponseDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));

        return buildOrderResponse(order);
    }
}