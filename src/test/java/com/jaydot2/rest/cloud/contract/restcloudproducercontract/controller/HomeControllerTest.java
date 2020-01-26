package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest {

    MockMvc mockMvc;
    HomeController controller;

    @BeforeEach
    void setUp() throws Exception {
        controller = new HomeController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("when a get request is made, then should return status Ok")
    void shouldReturnStatusOk() throws Exception {
        // Given
        String endpoint = "/workouts";

        // When
        mockMvc.perform(get(endpoint)).andExpect(status().isOk());

        // Then
    }
}