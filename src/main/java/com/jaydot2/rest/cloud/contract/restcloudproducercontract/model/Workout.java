package com.jaydot2.rest.cloud.contract.restcloudproducercontract.model;

import java.util.List;

public class Workout {

    private Long workoutId;
    private List<Exercise> exercises;

    public Workout(Long workoutId, List<Exercise> exercises) {
        this.workoutId = workoutId;
        this.exercises = exercises;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
