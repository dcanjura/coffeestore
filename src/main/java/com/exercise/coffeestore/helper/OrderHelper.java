package com.exercise.coffeestore.helper;

import com.exercise.coffeestore.dto.AdditionalDTO;
import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.dto.OrderItemDTO;
import com.exercise.coffeestore.mapper.AdditionalMapper;
import com.exercise.coffeestore.mapper.CoffeeMapper;
import com.exercise.coffeestore.model.Additional;
import com.exercise.coffeestore.model.Coffee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderHelper {

    private CoffeeMapper coffeeMapper;
    private AdditionalMapper additionalMapper;

    /**
     * Calculates total amount for coffees and additional
     * @param coffees
     * @return
     */
    public Double calculateTotal(List<Map<Coffee, List<Additional>>> coffees) {
        return coffees.stream()
                .flatMap(map -> map.entrySet().stream())
                .mapToDouble(entry -> {
                    Coffee coffee = entry.getKey();
                    List<Additional> additional = entry.getValue();

                    Double additionalTotal = additional.stream()
                            .mapToDouble(Additional::calculateCharge)
                            .sum();

                    return coffee.getPrice() + additionalTotal;
                })
                .sum();
    }

    public List<Map<Coffee, List<Additional>>> toDomain(List<OrderItemDTO> orderItemDTO) {
        return orderItemDTO.stream()
                .map(order -> {
                    Coffee coffee = coffeeMapper.toCoffee(order.coffee());

                    List<Additional> additional = order.additional()
                            .stream()
                            .map(additionalMapper::toAdditional)
                            .toList();

                    coffee.setName(buildItemName(coffee, additional));
                    Map<Coffee, List<Additional>> map = new HashMap<>();
                    map.put(coffee, additional);

                    return map;
                })
                .toList();
    }

    private String buildItemName(Coffee coffee, List<Additional> additional) {

        String additions = additional.stream()
                .sorted(
                        Comparator
                                .comparing(Additional::getCost)
                                .thenComparing(Additional::getDescription)
                )
                .map(a -> a.getDescription())
                .collect(Collectors.joining(" "));

        return additions + " " + coffee.getName();
    }

    public List<OrderItemDTO> toOrderItemDTO(List<Map<Coffee, List<Additional>>> items) {

        return items.stream()
                .flatMap(map -> map.entrySet().stream())
                .map(entry -> new OrderItemDTO(
                        coffeeMapper.toCoffeeDTO(entry.getKey()),
                        entry.getValue().stream()
                                .map(additionalMapper::toAdditionalDTO)
                                .toList()
                ))
                .toList();
    }

}
