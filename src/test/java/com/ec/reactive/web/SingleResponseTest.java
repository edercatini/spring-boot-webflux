package com.ec.reactive.web;

import com.ec.reactive.web.model.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingleResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void block() {
        Response response = webClient.get()
                .uri("math/square/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        assertNotNull(response);
        assertEquals(25, response.getOutput());
    }

    @Test
    void stepVerifier() {
        Mono<Response> responseMono = webClient.get()
                .uri("math/square/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(data -> data.getOutput().equals(25))
                .verifyComplete();
    }
}