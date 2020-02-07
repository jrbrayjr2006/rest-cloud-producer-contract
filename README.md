# Bray Rest Spring Cloud Producer Contract

## Overview

This project provides a sample reference application for implementing Spring Cloud Contract using REST from the producer perspective.

## Details

### Configuration

Use the https://start.spring.io site to bootstrap the application and setup the initial configuration.


### The Producer

The producer exposes a REST endpoint for consumers.

## Testing

This section describes important aspects of Spring Cloud Stream and Spring Cloud Contract testing.

- Abstract contract base test class
- Contract (YAML, Groovy, Java, or Kotlin)
- Configuration in the `POM.xml`

### Abstract Contract Test Class

The producer test(s) include the an abstract contract test class that Spring Cloud Contracts uses to autogenerate the producer contract test class.

```java
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
```

The above abstract class needs some explanation.  Let's start with the annotations.

The `@AutoConfigureMessageVerifier` annotation identifies this as a producer test class or abstract class.

The `@SpringBootTest` annotation is used to bring up the Spring context that is necessary for running the contract test(s).  Since contract testing is a form of integration testing, we need aspects of the Spring context to setup our test.

### The Contract (YAML)

Spring Cloud Contracts supports contracts written in Groovy, YAML, and recently added support for Kotlin.  Below are some sample contracts written using YAML.

#### Contract with an Array in the Response Body

```yaml
description: A simple contract that enforces the return of a workout with exercises
name: Workouts
request:
  url: /workout/1
  method: GET
response:
  status: 200
  headers:
    Content-Type: application/json
  body:
    workoutId: 1
    exercises:
    - name: "pushups"
      sets: 3
      reps: 10
    - name: "situps"
      sets: 3
      reps: 15
  matchers:
    body:
      exercises:
        - path: $.['reps']
          type: by_regex
          value: "[0-9]+"
        - path: $.['sets']
          type: by_regex
          value: "[0-9]+"
```

### Maven Testing Dependencies

When configuring the plugin, make sure to include the `<baseClassForTests>....AbstractContractTest</baseClassForTests>` in the configuration section of the sprinc cloud contract maven plugin.

```xml
	<dependencies>
        ...
        ...
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-contract-verifier</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-contract-maven-plugin</artifactId>
				<version>2.2.1.RELEASE</version>
				<extensions>true</extensions>
				<configuration>
                    <baseClassForTests>com.jaydot2.rest.cloud.contract.restcloudproducercontract.SimpleAbstractContractTest</baseClassForTests>
					<testFramework>JUNIT5</testFramework>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
```

## References

1. [Spring Cloud Contracts](https://cloud.spring.io/spring-cloud-contract)
2. [Testing a Spring Boot REST API Against a Contract with Spring Cloud Contract](https://reflectoring.io/consumer-driven-contract-provider-spring-cloud-contract/)
3. [Contract DSL](https://cloud.spring.io/spring-cloud-contract/multi/multi__contract_dsl.html)
4. [JUnit 5 Overview](https://junit.org/junit5/docs/current/user-guide/)