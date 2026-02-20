package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.OrderEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    @DisplayName("Should persist order")
    void shouldPersistOrder() {

        OrderEntity order = new OrderEntity();
        order.setDescription("Test Order");

        OrderEntity saved = repository.save(order);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDescription()).isEqualTo("Test Order");
    }
}
