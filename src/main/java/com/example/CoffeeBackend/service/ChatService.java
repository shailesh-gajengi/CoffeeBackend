package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final CoffeeRepository coffeeRepository;
    private final GeminiService geminiService;

    public ChatService(
            CoffeeRepository coffeeRepository,
            GeminiService geminiService
    ) {
        this.coffeeRepository = coffeeRepository;
        this.geminiService = geminiService;
    }

    public List<Coffee> searchCoffee(String message) {

        if (message == null || message.isBlank()) {
            return List.of();
        }

        // Convert user's message into a vector
        List<Float> embedding =
                geminiService.generateEmbedding(message);

        // Convert List<Float> into PostgreSQL vector format
        String vector =
                embedding.toString()
                        .replace(" ", "");

        // Find the most semantically similar coffees
        return coffeeRepository.findSimilarCoffees(vector);
    }
}