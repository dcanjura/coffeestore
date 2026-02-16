package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.Additional;

import java.util.List;

public record OrderItemDTO(CoffeeDTO coffee, List<AdditionalDTO> additional) {
}
