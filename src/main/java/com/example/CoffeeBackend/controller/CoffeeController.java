package com.example.CoffeeBackend.controller;

import com.example.CoffeeBackend.entity.Coffee;
import com.example.CoffeeBackend.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffee() {
        return ResponseEntity.ok(coffeeService.getAllCoffee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        return ResponseEntity.ok(coffeeService.getCoffeeById(id));
    }

    @PostMapping
    public ResponseEntity<Coffee> addCoffee( @Valid @RequestBody Coffee coffee) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(coffeeService.saveCoffee(coffee));
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<Coffee>> addAllCoffee(
            @RequestBody List<Coffee> coffees) {

        List<Coffee> saved = coffees.stream()
                .map(coffeeService::saveCoffee)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(
            @PathVariable Long id,
            @Valid @RequestBody Coffee coffee) {

        return ResponseEntity.ok(coffeeService.updateCoffee(id, coffee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoffee(@PathVariable Long id) {

        coffeeService.deleteCoffee(id);

        return ResponseEntity.ok("Coffee Deleted Successfully");
    }
}