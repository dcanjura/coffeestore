package com.exercise.coffeestore.mapper;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.model.Coffee;

public class CoffeeMapper {

    public Coffee toCoffee(CoffeeDTO coffeeDTO) {
        return new Coffee(coffeeDTO.id(), coffeeDTO.name(), coffeeDTO.description(), coffeeDTO.enabled(), coffeeDTO.price());
    }

    public CoffeeDTO toCoffeeDTO(Coffee coffee) {
        return new CoffeeDTO(
                coffee.getId(),
                coffee.getName(),
                coffee.getDescription(),
                coffee.isEnabled(),
                coffee.getPrice()
        );
    }
}
