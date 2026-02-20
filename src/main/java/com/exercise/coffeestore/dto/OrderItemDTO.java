package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.OrderItemEntity;

import java.util.List;

public record OrderItemDTO(Long id, CoffeeDTO coffee, List<AdditionalDTO> additional) {
    public static List<OrderItemEntity> toDomain(List<OrderItemDTO> orderItemDTO) {
        if(orderItemDTO == null || orderItemDTO.isEmpty()) {
            return List.of();
        }

        return orderItemDTO.stream()
                .map(dto -> {
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setId(dto.id());
                    orderItemEntity.setCoffee(CoffeeDTO.toCoffee(dto.coffee()));
                    orderItemEntity.setAdditional(
                            dto.additional
                                    .stream()
                                    .map(AdditionalDTO::toAdditional)
                                    .toList()
                    );

                    return orderItemEntity;
                })
                .toList();
    }

    public static OrderItemDTO toDTO(OrderItemEntity orderItemEntity) {
        return new OrderItemDTO(
                orderItemEntity.getId(),
                CoffeeDTO.toDTO(orderItemEntity.getCoffee()),
                orderItemEntity.getAdditional()
                        .stream()
                        .map(AdditionalDTO::toAdditionalDTO)
                        .toList()
        );
    }

    public static List<OrderItemDTO> toDTO(List<OrderItemEntity> orderItemEntity) {
        if(orderItemEntity == null || orderItemEntity.isEmpty()) {
            return List.of();
        }

        return orderItemEntity.stream()
                .map(OrderItemDTO::toDTO)
                .toList();
    }
}
