package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
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

    public HomeController() {}

    @GetMapping(value = "/exercise", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> exercises() {
        Exercise contentBody = new Exercise("pushups", 3, 10);
        ResponseEntity<Exercise> result = ResponseEntity.ok(contentBody);
        return result;
    }


    @GetMapping( value = "/workout/{workoutId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Workout> workout(@PathVariable Long workoutId) {
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise("pushups", 2, 10);
        Exercise exercise2 = new Exercise("situps", 3, 10);
        exercises.add(exercise1);
        exercises.add(exercise2);
        Workout workout = new Workout(workoutId, exercises);
        ResponseEntity<Workout> response = ResponseEntity.status(HttpStatus.OK).body(workout);
        return response;
    }
}
