package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.service.WorkoutService;
import org.apache.commons.codec.Charsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest {

    MockMvc mockMvc;
    HomeController controller;
    private WorkoutService mockWorkoutService;

    @BeforeEach
    void setUp() throws Exception {
        mockWorkoutService = mock(WorkoutService.class);
        controller = new HomeController(mockWorkoutService);
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

    @Test
    @DisplayName("Given a workout ID, when a request for a workout is made, the workout service should be invoked")
    void shouldInvokeGetWorkoutOnWorkoutService() {
        // Given
        Long someworkoutId = new Random().nextLong();
        Workout mockWorkout = mock(Workout.class);
        // When

        when(mockWorkoutService.getWorkout(someworkoutId)).thenReturn(mockWorkout);
        controller.workout(someworkoutId);
        // Then
        verify(mockWorkoutService).getWorkout(someworkoutId);
    }
}