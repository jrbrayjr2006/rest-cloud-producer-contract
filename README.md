# Bray Rest Spring Cloud Producer Contract

## Overview

This project provides a sample reference application for implementing Spring Cloud Contract using REST from the producer perspective.

## Details

### Configuration

Use the https://start.spring.io site to bootstrap the application and setup the initial configuration.


### The Producer

The producer exposes a REST endpoint for consumers.

### The Contract

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


## Testing

This section describes important aspects of Spring Cloud Stream and Spring Cloud Contract testing.

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