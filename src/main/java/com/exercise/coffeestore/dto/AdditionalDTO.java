package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.Additional;

public record AdditionalDTO(Long id, String description, Double cost, Double total) {
    public static Additional toAdditional(AdditionalDTO additionalDTO) {
        return new Additional(
                null,
                additionalDTO.description(),
                additionalDTO.cost(),
                additionalDTO.total()
        );
    }

    public static AdditionalDTO toAdditionalDTO(Additional additional) {
        return new AdditionalDTO(
                additional.getId(),
                additional.getDescription(),
                additional.getCost(),
                additional.getCombineCost()
        );
    }
}
