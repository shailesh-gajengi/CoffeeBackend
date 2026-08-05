package com.example.CoffeeBackend.exception;

public class CoffeeNotFoundException extends RuntimeException {

    public CoffeeNotFoundException(String message) {
        super(message);
    }
}