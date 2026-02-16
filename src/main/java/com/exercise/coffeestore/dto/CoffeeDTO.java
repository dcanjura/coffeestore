package com.exercise.coffeestore.dto;

public record CoffeeDTO(Long id, String name, String description, Boolean enabled, Double price) {
}
