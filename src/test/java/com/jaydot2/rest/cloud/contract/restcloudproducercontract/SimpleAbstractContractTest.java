package com.jaydot2.rest.cloud.contract.restcloudproducercontract;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

@SpringBootTest( classes = { RestCloudProducerContractApplication.class})
@AutoConfigureMessageVerifier
public abstract class SimpleAbstractContractTest {

    @BeforeEach
    void setUp() {}
}
