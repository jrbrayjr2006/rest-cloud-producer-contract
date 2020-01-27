package com.jaydot2.rest.cloud.contract.restcloudproducercontract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class HomeController {

    public HomeController() {}

    @GetMapping(value = "/workouts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> workouts() {
        String contentBody = "Hello";
        ResponseEntity<String> result = ResponseEntity.ok(contentBody);
        return result;
    }
}
