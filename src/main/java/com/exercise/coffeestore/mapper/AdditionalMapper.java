package com.exercise.coffeestore.mapper;

import com.exercise.coffeestore.dto.AdditionalDTO;
import com.exercise.coffeestore.model.Additional;

public class AdditionalMapper {
    public Additional toAdditional(AdditionalDTO additionalDTO) {
        return new Additional(
                null,
                additionalDTO.description(),
                additionalDTO.cost(),
                additionalDTO.total()
        );
    }

    public AdditionalDTO toAdditionalDTO(Additional additional) {
        return new AdditionalDTO(
                additional.getId(),
                additional.getDescription(),
                additional.getCost(),
                additional.getCombineCost()
        );
    }
}
