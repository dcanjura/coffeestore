package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(Long id, String description, List<OrderItemDTO> orderItemDTO, Integer totalItems, Double total, LocalDateTime date) {
    public static OrderEntity toOrder(OrderDTO orderDTO){
        return new OrderEntity(orderDTO.id(), orderDTO.description(), OrderItemDTO.toDomain(orderDTO.orderItemDTO()), orderDTO.totalItems(), orderDTO.total(), orderDTO.date);
    }

    public static OrderDTO toDTO(OrderEntity orderEntity){
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getDescription(),
                OrderItemDTO.toDTO(orderEntity.getOrderItem()),
                orderEntity.getTotalItems(),
                orderEntity.getTotal(),
                orderEntity.getDate()
        );
    }
}
