package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmbeddingService {

    private final CoffeeRepository coffeeRepository;
    private final GeminiService geminiService;

    public EmbeddingService(
            CoffeeRepository coffeeRepository,
            GeminiService geminiService
    ) {
        this.coffeeRepository = coffeeRepository;
        this.geminiService = geminiService;
    }

    @Transactional
    public void generateAndStoreEmbedding(Long coffeeId) {

        Coffee coffee = coffeeRepository
                .findById(coffeeId)
                .orElseThrow(() ->
                        new RuntimeException("Coffee not found")
                );

        String text =
                coffee.getName()
                        + "\n"
                        + coffee.getDescription()
                        + "\n"
                        + coffee.getCategory();

        List<Float> embedding =
                geminiService.generateEmbedding(text);

        String vector =
                embedding.toString()
                        .replace(" ", "");

        coffeeRepository.updateEmbedding(
                coffeeId,
                vector
        );
    }
    @Transactional
    public void generateAllEmbeddings() {

        List<Coffee> coffees =
                coffeeRepository.findCoffeesWithoutEmbedding();

        System.out.println(
                "Coffees without embeddings: " + coffees.size()
        );

        int count = 0;

        for (Coffee coffee : coffees) {

            try {

                generateAndStoreEmbedding(coffee.getId());

                count++;

                System.out.println(
                        "Embedding generated for ID: "
                                + coffee.getId()
                                + " (" + count + "/" + coffees.size() + ")"
                );

                // Small delay between requests
                Thread.sleep(1000);

            } catch (Exception e) {

                System.out.println(
                        "Failed for ID: "
                                + coffee.getId()
                                + " : "
                                + e.getMessage()
                );
            }
        }

        System.out.println(
                "Embedding generation finished. Generated: " + count
        );
    }
    @Transactional
    public int generateEmbeddingsBatch(int batchSize) {

        List<Coffee> coffees =
                coffeeRepository.findCoffeesWithoutEmbedding()
                        .stream()
                        .limit(batchSize)
                        .toList();

        int count = 0;

        for (Coffee coffee : coffees) {

            try {

                generateAndStoreEmbedding(coffee.getId());

                count++;

                System.out.println(
                        "Embedding generated for ID: "
                                + coffee.getId()
                );

                Thread.sleep(1000);

            } catch (Exception e) {

                System.out.println(
                        "Failed for ID: "
                                + coffee.getId()
                                + " : "
                                + e.getMessage()
                );
            }
        }

        return count;
    }
}