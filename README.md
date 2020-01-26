# Bray Rest Spring Cloud Producer Contract

## Overview

This project provides a sample reference application for implementing Spring Cloud Contract.

## Details

### Configuration

Use the https://start.spring.io site to bootstrap the application and setup the initial configuration.


### The Producer

The producer uses an interface to send a message to the output channel.


## Testing

This section describes important aspects of Spring Cloud Stream and Spring Cloud Contract testing.

### Gradle Testing Dependencies

```groovy
dependencies {
    ...
    testImplementation('org.springframework.boot:spring-boot-starter-test')
    testImplementation('org.springframework.cloud:spring-cloud-starter-contract-stub-runner')
    testImplementation('org.springframework.cloud:spring-cloud-starter-contract-verifier')
    testImplementation('org.springframework.cloud:spring-cloud-stream-test-support')

    // Junit 5
    testImplementation('org.junit.jupiter:junit-jupiter-api:5.3.1')
    testImplementation('org.junit.jupiter:junit-jupiter-params:5.3.1')
    testImplementation('org.mockito:mockito-junit-jupiter:2.23.0')
    testRuntimeOnly('org.junit.jupiter:junit-jupiter-engine:5.3.1')
}
```

## References

1. [Spring Cloud Contracts](https://cloud.spring.io/spring-cloud-contract)
2. [Testing a Spring Boot REST API Against a Contract with Spring Cloud Contract](https://reflectoring.io/consumer-driven-contract-provider-spring-cloud-contract/)
3. [Contract DSL](https://cloud.spring.io/spring-cloud-contract/multi/multi__contract_dsl.html)
4. [JUnit 5 Overview](https://junit.org/junit5/docs/current/user-guide/)