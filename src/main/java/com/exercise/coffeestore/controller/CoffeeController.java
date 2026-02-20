package com.exercise.coffeestore.controller;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {

    private final CoffeeService service;
    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CoffeeDTO>> getAllCoffees(Pageable pageable) {
        return ResponseEntity.ok(service.getAllCoffees(pageable));
    }

    @GetMapping("nameOrDescription")
    public ResponseEntity<Optional<CoffeeDTO>> searchByNameOrDescription(@Param("name") String name, @Param("description") String description) {
        return ResponseEntity.ok(service.getCoffeeByValue(name, description));
    }

    @PostMapping
    public ResponseEntity<CoffeeDTO> createCoffee(@Valid @RequestBody CoffeeDTO coffeeDTO) {
        return ResponseEntity.ok(service.createCoffee(coffeeDTO));
    }
}
