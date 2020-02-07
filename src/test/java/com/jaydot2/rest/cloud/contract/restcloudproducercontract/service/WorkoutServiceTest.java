package com.jaydot2.rest.cloud.contract.restcloudproducercontract.service;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutServiceTest {

    WorkoutService service;

    @BeforeEach
    void setUp() {
        service = new WorkoutService();
    }

    @Test
    @DisplayName("Given a workout ID, when getWorkout is called, then should return workout")
    void shouldReturnWorkout() {
        // Given
        Long workoutId = new Random().nextLong();
        // When
        Workout actualWorkout = service.getWorkout(workoutId);
        // Then
        assertNotNull(actualWorkout);
    }
}