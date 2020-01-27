# Bray Rest Spring Cloud Producer Contract

## Overview

This project provides a sample reference application for implementing Spring Cloud Contract.

## Details

### Configuration

Use the https://start.spring.io site to bootstrap the application and setup the initial configuration.


### The Producer

The producer exposes a REST endpoint for consumers.


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