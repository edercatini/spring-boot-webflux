package com.ec.reactive.web;

import com.ec.reactive.web.model.MultiplyRequestDto;
import com.ec.reactive.web.model.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class HeadersTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void stepVerifier() {
        MultiplyRequestDto request = new MultiplyRequestDto(5, 2);

        Mono<Response> responseMono = webClient.post()
                .uri("math/multiply")
                .headers(h -> h.set("key", "value"))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextMatches(data -> data.getOutput().equals(request.getFirst() * request.getSecond()))
                .verifyComplete();
    }
}