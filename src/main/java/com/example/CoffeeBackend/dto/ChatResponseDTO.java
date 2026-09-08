package com.example.CoffeeBackend.dto;

import java.util.List;

public class ChatResponseDTO {

    private String reply;
    private List<Long> recommendedCoffeeIds;

    public ChatResponseDTO() {
    }

    public ChatResponseDTO(
            String reply,
            List<Long> recommendedCoffeeIds
    ) {
        this.reply = reply;
        this.recommendedCoffeeIds = recommendedCoffeeIds;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<Long> getRecommendedCoffeeIds() {
        return recommendedCoffeeIds;
    }

    public void setRecommendedCoffeeIds(List<Long> recommendedCoffeeIds) {
        this.recommendedCoffeeIds = recommendedCoffeeIds;
    }
}