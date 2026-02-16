package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.dto.OrderItemDTO;
import com.exercise.coffeestore.helper.OrderHelper;
import com.exercise.coffeestore.model.Additional;
import com.exercise.coffeestore.model.AuditEvent;
import com.exercise.coffeestore.model.Coffee;
import com.exercise.coffeestore.model.Order;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    OrderRepository repository;
    AuditRepository auditRepository;
    OrderHelper helper = new OrderHelper();

    public OrderService(OrderRepository repository, AuditRepository auditRepository) {
        this.repository = repository;
        this.auditRepository = auditRepository;
    }

    /**
     * Retrieve a list of all orders.
     * @return
     */
    public List<OrderDTO> getAllOrders() {
        return repository.getAllOrders()
                .stream()
                .map(u -> new OrderDTO(u.getId(), u.getDescription(), helper.toOrderItemDTO(u.getCoffees()), u.getTotalItems(), u.getTotal(), u.getDate()))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new order. This request should include Description, Coffees, Additional, Items
     * @param description
     * @param orderItemDTO
     * @param totalItems
     * @return
     */
    public Optional<OrderDTO> createOrder(String description, List<OrderItemDTO> orderItemDTO, Integer totalItems) {
        List<Map<Coffee, List<Additional>>> coffees = helper.toDomain(orderItemDTO);
        Double total = helper.calculateTotal(coffees);

        return repository.createOrder(new Order(null, description, coffees, totalItems, total, LocalDateTime.now()))
                .map(u -> {
                    auditRepository.save(new AuditEvent(
                            null,
                            "New order added: " + description,
                            LocalDateTime.now(),
                            "Total: " + total
                    ));

                    return new OrderDTO(u.getId(), u.getDescription(), helper.toOrderItemDTO(coffees), totalItems, total, LocalDateTime.now());
                });
    }
}
