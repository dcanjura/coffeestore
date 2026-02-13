package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.Coffee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CoffeeRepository {
    private final Map<Long, Coffee> coffeeMap = new HashMap<>();
    private Long idCounter = 1L;

    public List<Coffee> getAllCoffees(){
        return new ArrayList<>(coffeeMap.values());
    };

    public Optional<Coffee> getCoffeeByValue(String value) {
        return coffeeMap.values().stream()
                .filter(coffee -> coffee.getName().equalsIgnoreCase(value) || coffee.getDescription().equalsIgnoreCase(value))
                .findFirst();
    }

    public Optional<Coffee> createCoffee(Coffee coffee) {
        Coffee newCoffee = new Coffee(idCounter++, coffee.getName(), coffee.getDescription(), coffee.isEnabled());
        coffeeMap.put(newCoffee.getId(), newCoffee);
        return Optional.of(newCoffee);
    }
}
