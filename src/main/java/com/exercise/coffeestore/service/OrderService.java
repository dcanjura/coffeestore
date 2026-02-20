package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.AuditEventDTO;
import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.dto.OrderItemDTO;
import com.exercise.coffeestore.helper.OrderHelper;
import com.exercise.coffeestore.model.OrderEntity;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        return repository.findAll(pageable)
                .map(u -> new OrderDTO(u.getId(), u.getDescription(), OrderItemDTO.toDTO(u.getOrderItem()), u.getTotalItems(), u.getTotal(), u.getDate()));

    }

    /**
     * Creates a new order. This request should include Description, Coffees, Additional, Items
     * @param orderDTO value
     * @return saved orderDTO
     */
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = repository.save(OrderDTO.toOrder(orderDTO));
        AuditEventDTO auditEventDTO = new AuditEventDTO(null, "CREATE", LocalDateTime.now(), "New order placed");
        auditRepository.save(AuditEventDTO.toDomain(auditEventDTO));
        return OrderDTO.toDTO(orderEntity);
    }
}
