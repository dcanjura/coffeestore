package com.exercise.coffeestore.controller;

import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService service;
    public OrderController(OrderService service) { this.service = service; }

    @GetMapping // Retrieve a list of all orders.
    public ResponseEntity<Page<OrderDTO>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok().body(service.getAllOrders(pageable));
    }

    @PostMapping // Creates a new order. This request should include Description, Coffees, Additional, Items
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(service.createOrder(orderDTO));
    }
}
