package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.OrderDTO;
import com.exercise.coffeestore.model.OrderEntity;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;
    @Mock
    private AuditRepository auditRepository;


    @InjectMocks
    private OrderService service;

    @Test
    void shouldSaveOrder() {

        OrderEntity entity = new OrderEntity();
        entity.setId(1L);

        when(repository.save(any())).thenReturn(entity);

        OrderDTO input = new OrderDTO(
                null,
                "Test order",
                List.of(),
                10,
                100.10,
                LocalDateTime.now()
        );

        OrderDTO result = service.createOrder(input);

        assertNotNull(result);
        verify(repository, times(1)).save(any());
    }
}
