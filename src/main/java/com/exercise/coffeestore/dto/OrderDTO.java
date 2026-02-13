package com.exercise.coffeestore.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(Long id, String description, List<CoffeeDTO> coffeeDTOS, Integer totalItems, Double total, LocalDateTime date) {
}
