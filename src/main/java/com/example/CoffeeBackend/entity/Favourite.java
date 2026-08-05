package com.example.CoffeeBackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favourite")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long coffeeId;

    public Favourite() {
    }

    public Favourite(Long id, Long userId, Long coffeeId) {
        this.id = id;
        this.userId = userId;
        this.coffeeId = coffeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(Long coffeeId) {
        this.coffeeId = coffeeId;
    }
}