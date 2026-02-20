package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.CoffeeEntity;

public record CoffeeDTO(Long id, String name, String description, Boolean enabled, Double price) {
    public static CoffeeEntity toCoffee(CoffeeDTO coffeeDTO) {
        return new CoffeeEntity(coffeeDTO.id(), coffeeDTO.name(), coffeeDTO.description(), coffeeDTO.enabled(), coffeeDTO.price());
    }

    public static CoffeeDTO toDTO(CoffeeEntity coffeeEntity) {
        return new CoffeeDTO(
                coffeeEntity.getId(),
                coffeeEntity.getName(),
                coffeeEntity.getDescription(),
                coffeeEntity.isEnabled(),
                coffeeEntity.getPrice()
        );
    }
}
