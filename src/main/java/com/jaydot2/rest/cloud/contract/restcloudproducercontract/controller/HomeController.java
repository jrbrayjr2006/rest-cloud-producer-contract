package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.service.WorkoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Exercise;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class HomeController {

    private WorkoutService workoutService;

    public HomeController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping(value = "/exercise", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> exercises() {
        Exercise contentBody = new Exercise("pushups", 3, 10);
        ResponseEntity<Exercise> result = ResponseEntity.ok(contentBody);
        return result;
    }


    @GetMapping( value = "/workout/{workoutId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Workout> workout(@PathVariable Long workoutId) {
        Workout workout = workoutService.getWorkout(workoutId);
        ResponseEntity<Workout> response = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(workout);
        return response;
    }
}
