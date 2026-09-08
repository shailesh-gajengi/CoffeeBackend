package com.example.CoffeeBackend.controller;

import com.example.CoffeeBackend.dto.ChatRequestDTO;
import com.example.CoffeeBackend.dto.ChatResponseDTO;
import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.service.ChatService;
import com.example.CoffeeBackend.service.EmbeddingService;
import com.example.CoffeeBackend.service.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final EmbeddingService embeddingService;
    private final ChatService chatService;
    private final GeminiService geminiService;

    public ChatController(
            ChatService chatService,
            GeminiService geminiService,
            EmbeddingService embeddingService
    ) {
        this.chatService = chatService;
        this.geminiService = geminiService;
        this.embeddingService = embeddingService;
    }
    @PostMapping("/embed/{id}")
    public String generateEmbedding(
            @PathVariable Long id
    ) {

        embeddingService.generateAndStoreEmbedding(id);

        return "Embedding generated successfully for coffee ID: " + id;
    }
    @PostMapping("/embed-batch")
    public String generateEmbeddingBatch() {

        int count =
                embeddingService.generateEmbeddingsBatch(5);

        return "Generated embeddings for " + count + " coffees";
    }
    @PostMapping("/embed-all")
    public String generateAllEmbeddings() {

        embeddingService.generateAllEmbeddings();

        return "Embedding generation completed";
    }

    @GetMapping("/embedding-test")
    public String testEmbedding() {

        int size = geminiService.testEmbedding(
                "Espresso, strong and rich coffee with a bold flavor"
        );

        return "Embedding size = " + size;
    }
    @PostMapping
    public ChatResponseDTO chat(
            @RequestBody ChatRequestDTO request
    ) {

        // 1. Retrieve relevant coffees from database
        List<Coffee> coffees =
                chatService.searchCoffee(request.getMessage());

        // 2. Keep only the first 3 recommendations
        List<Coffee> recommendedCoffees =
                coffees.stream()
                        .limit(3)
                        .toList();

        // 3. Convert retrieved coffees into context for Gemini
        String coffeeContext =
                recommendedCoffees.stream()
                        .map(coffee ->
                                "Name: " + coffee.getName()
                                        + ", Description: " + coffee.getDescription()
                                        + ", Category: " + coffee.getCategory()
                                        + ", Price: " + coffee.getPrice()
                        )
                        .collect(Collectors.joining("\n"));

        // 4. Generate AI response using retrieved coffee context
        String reply =
                geminiService.generateResponse(
                        request.getMessage(),
                        coffeeContext
                );

        // 5. Get IDs of recommended coffees
        List<Long> recommendedCoffeeIds =
                recommendedCoffees.stream()
                        .map(Coffee::getId)
                        .toList();

        // 6. Return AI reply + actual coffee IDs
        return new ChatResponseDTO(
                reply,
                recommendedCoffeeIds
        );
    }
}