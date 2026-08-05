package com.example.CoffeeBackend.service;

import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.exception.CoffeeNotFoundException;
import com.example.CoffeeBackend.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Coffee saveCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Coffee getCoffeeById(Long id) {

        return coffeeRepository.findById(id)
                .orElseThrow(() ->
                        new CoffeeNotFoundException("Coffee not found with id : " + id));
    }

    public Coffee updateCoffee(Long id, Coffee coffee) {

        Coffee existingCoffee = coffeeRepository.findById(id)
                .orElseThrow(() ->
                        new CoffeeNotFoundException("Coffee not found with id : " + id));

        existingCoffee.setName(coffee.getName());
        existingCoffee.setDescription(coffee.getDescription());
        existingCoffee.setPrice(coffee.getPrice());
        existingCoffee.setImageUrl(coffee.getImageUrl());
        existingCoffee.setCategory(coffee.getCategory());

        return coffeeRepository.save(existingCoffee);
    }

    public void deleteCoffee(Long id) {

        if (!coffeeRepository.existsById(id)) {
            throw new CoffeeNotFoundException("Coffee not found with id : " + id);
        }

        coffeeRepository.deleteById(id);
    }
}