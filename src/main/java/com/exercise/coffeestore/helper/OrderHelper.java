package com.exercise.coffeestore.helper;

import com.exercise.coffeestore.dto.AdditionalDTO;
import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.dto.OrderItemDTO;
import com.exercise.coffeestore.model.Additional;
import com.exercise.coffeestore.model.CoffeeEntity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderHelper {

    /**
     * Calculates total amount for coffees and additional
     * @param coffees list
     * @return a total amount
     */
    public Double calculateTotal(List<Map<CoffeeEntity, List<Additional>>> coffees) {
        return coffees.stream()
                .flatMap(map -> map.entrySet().stream())
                .mapToDouble(entry -> {
                    CoffeeEntity coffeeEntity = entry.getKey();
                    List<Additional> additional = entry.getValue();

                    Double additionalTotal = additional.stream()
                            .mapToDouble(Additional::calculateCharge)
                            .sum();

                    return coffeeEntity.getPrice() + additionalTotal;
                })
                .sum();
    }

    public List<Map<CoffeeEntity, List<Additional>>> toDomain(List<OrderItemDTO> orderItemDTO) {
        return orderItemDTO.stream()
                .map(order -> {
                    CoffeeEntity coffeeEntity = CoffeeDTO.toCoffee(order.coffee());

                    List<Additional> additional = order.additional()
                            .stream()
                            .map(AdditionalDTO::toAdditional)
                            .toList();

                    coffeeEntity.setName(buildItemName(coffeeEntity, additional));
                    Map<CoffeeEntity, List<Additional>> map = new HashMap<>();
                    map.put(coffeeEntity, additional);

                    return map;
                })
                .toList();
    }

    private String buildItemName(CoffeeEntity coffeeEntity, List<Additional> additional) {

        String additions = additional.stream()
                .sorted(
                        Comparator
                                .comparing(Additional::getCost)
                                .thenComparing(Additional::getDescription)
                )
                .map(Additional::getDescription)
                .collect(Collectors.joining(" "));

        return additions + " " + coffeeEntity.getName();
    }

}
