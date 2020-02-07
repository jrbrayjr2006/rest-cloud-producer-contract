package com.jaydot2.rest.cloud.contract.restcloudproducercontract.model;

public class Exercise {

    private String name;
    private Integer sets;
    private Integer reps;

    public Exercise(String name, Integer sets, Integer reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    public String getName() {
        return name;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }
}
