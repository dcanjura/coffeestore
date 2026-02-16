package com.exercise.coffeestore.controller;

import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.service.OrderService;
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
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok().body(service.getAllOrders());
    }

    @PostMapping // Creates a new order. This request should include Description, Coffees, Additional, Items
    public ResponseEntity<Optional<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(service.createOrder(orderDTO.description(), orderDTO.orderItemDTO(), orderDTO.totalItems()));
    }
}
