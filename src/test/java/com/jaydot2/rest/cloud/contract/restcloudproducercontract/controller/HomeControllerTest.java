package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import org.apache.commons.codec.Charsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        String endpoint = "/exercise";

        // When
        mockMvc.perform(get(endpoint)).andExpect(status().isOk());

        // Then
    }

    @Test
    @DisplayName("Given a workout id, when a get request is made, then a workout should be returned")
    void shouldReturnWorkout() throws Exception {
        // Given
        Long someWorkoutId = 0L;
        String endpoint = "/workout/{workoutId}";

        // When
        MvcResult result = mockMvc.perform(get(endpoint, someWorkoutId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();
        response.getContentAsString(Charsets.UTF_8);
        // Then
    }
}