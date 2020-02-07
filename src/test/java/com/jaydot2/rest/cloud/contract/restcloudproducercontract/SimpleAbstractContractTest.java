package com.jaydot2.rest.cloud.contract.restcloudproducercontract;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller.HomeController;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Exercise;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.service.WorkoutService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest( classes = { RestCloudProducerContractApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public abstract class SimpleAbstractContractTest {

    HomeController controller;
    private WorkoutService mockWorkoutService;

    @BeforeEach
    void setUp() {
        Long workoutId = 1L;
        mockWorkoutService = mock(WorkoutService.class);
        controller = new HomeController(mockWorkoutService);
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise("pushups", 2, 10);
        Exercise exercise2 = new Exercise("situps", 3, 10);
        exercises.add(exercise1);
        exercises.add(exercise2);
        Workout workout = new Workout(workoutId, exercises);
        when(mockWorkoutService.getWorkout(workoutId)).thenReturn(workout);
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(controller);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
