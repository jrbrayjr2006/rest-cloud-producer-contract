package com.jaydot2.rest.cloud.contract.restcloudproducercontract.service;

import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Exercise;
import com.jaydot2.rest.cloud.contract.restcloudproducercontract.model.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {
    public Workout getWorkout(Long workoutId) {
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise("pushups", 3, 15);
        Exercise exercise2 = new Exercise("situps", 3, 10);
        exercises.add(exercise1);
        exercises.add(exercise2);
        Workout workout = new Workout(workoutId, exercises);
        return workout;
    }
}
