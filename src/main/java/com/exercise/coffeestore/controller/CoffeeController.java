package com.exercise.coffeestore.controller;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {
    private final CoffeeService service;

    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    @GetMapping // Retrieve a list of all coffees available in the store. Each coffee should include its id, name, description, and enabled status.
    public ResponseEntity<List<CoffeeDTO>> getAllCoffees() {
        return ResponseEntity.ok(service.getAllCoffees());
    }

    @GetMapping("/{value}") // Search for a coffee by name or description. The search is case-insensitive.
    public ResponseEntity<Optional<CoffeeDTO>> searchByValue(@PathVariable String value) {
        return ResponseEntity.ok(service.getCoffeeByValue(value));
    }

    @PostMapping // Add a new coffee to the store. The request should include the name, description, and enabled status of the coffee.
    public ResponseEntity<Optional<CoffeeDTO>> createCoffee(@RequestBody CoffeeDTO coffeeDTO) {
        return ResponseEntity.ok(service.createCoffee(coffeeDTO.name(), coffeeDTO.description(), coffeeDTO.enabled()));
    }
}
