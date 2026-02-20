package com.exercise.coffeestore.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return 200 when calling orders endpoint")
    void shouldReturnOrders() throws Exception {

        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk());
    }
}
