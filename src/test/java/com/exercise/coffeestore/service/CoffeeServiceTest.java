package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.model.CoffeeEntity;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.CoffeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeServiceTest {

    @Mock
    private CoffeeRepository repository;
    @Mock
    private AuditRepository auditRepository;


    @InjectMocks
    private CoffeeService service;

    @Test
    void shouldCreateCoffee() {

        CoffeeEntity entity = new CoffeeEntity(
                1L,
                "Latte",
                "Hot coffee",
                true,
                3.5
        );

        when(repository.save(any())).thenReturn(entity);

        CoffeeDTO input = new CoffeeDTO(
                null,
                "Latte",
                "Hot coffee",
                true,
                3.5
        );

        CoffeeDTO result = service.createCoffee(input);

        assertNotNull(result);
        assertEquals("Latte", result.name());
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldReturnPagedCoffees() {

        CoffeeEntity entity = new CoffeeEntity(
                1L,
                "Espresso",
                "Strong coffee",
                true,
                2.0
        );

        Page<CoffeeEntity> page = new PageImpl<>(List.of(entity));

        when(repository.findAll(any(PageRequest.class)))
                .thenReturn(page);

        Page<CoffeeDTO> result =
                service.getAllCoffees(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Espresso", result.getContent().get(0).name());
    }
}
