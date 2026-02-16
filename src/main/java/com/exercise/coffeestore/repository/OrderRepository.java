package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();
    private Long counter = 1L;

    public List<Order> getAllOrders() { return new ArrayList<>(orders.values()); }

    public Optional<Order> createOrder(Order order) {
        Order newOrder = new Order(counter++, order.getDescription(), order.getCoffees(), order.getTotalItems(), order.getTotal(), order.getDate());
        orders.put(newOrder.getId(), newOrder);
        return Optional.of(newOrder);
    }
}
