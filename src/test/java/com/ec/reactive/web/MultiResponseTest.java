package com.ec.reactive.web;

import com.ec.reactive.web.model.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class MultiResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void stepVerifier() {
        Flux<Response> responseFlux = webClient.get()
                .uri("math/table/{input}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextCount(10L)
                .verifyComplete();
    }
}