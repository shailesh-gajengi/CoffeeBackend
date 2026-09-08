package com.example.CoffeeBackend.service;

import com.google.genai.Client;
import com.google.genai.errors.ClientException;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService() {
        this.client = new Client();
    }

    public String generateResponse(String message, String coffeeContext) {

        String prompt = """
            You are a friendly AI coffee assistant.

            User message:
            %s

            Available coffees from our coffee database:
            %s

            Recommend suitable coffee only from the available coffees above.
            Explain briefly why the recommendation fits the user's request.
            Do not invent coffee names or information that is not provided.
            """.formatted(message, coffeeContext);

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-3.6-flash",
                            prompt,
                            null
                    );

            return response.text();

        } catch (ClientException e) {

            if (e.code() == 429) {
                return "I'm temporarily unavailable because the AI request limit has been reached. Please try again shortly.";
            }

            return "Sorry, I couldn't generate a response right now.";
        }
    }
    public List<Float> generateEmbedding(String text) {

        EmbedContentResponse response =
                client.models.embedContent(
                        "gemini-embedding-2",
                        text,
                        null
                );

        return response.embeddings()
                .orElseThrow()
                .get(0)
                .values()
                .orElseThrow();
    }
    public int testEmbedding(String text) {

        List<Float> embedding = generateEmbedding(text);

        return embedding.size();
    }
}