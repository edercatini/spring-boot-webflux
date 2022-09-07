package com.ec.reactive.web.service;

import com.ec.reactive.web.exception.InputValidationException;
import com.ec.reactive.web.model.MultiplyRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class MathServiceTest {

    @InjectMocks
    private MathService mathService;

    @Test
    @DisplayName("Must find square from given input")
    void findSquare() {
        Integer input = 10;

        StepVerifier.create(mathService.findSquare(input))
                .expectNextMatches(element -> element.getOutput().equals(input * input))
                .verifyComplete();
    }

    @Test
    @DisplayName("Must handle input exception on square method")
    void findSquareException() {
        StepVerifier.create(mathService.findSquare(-2))
                .expectNextCount(0L)
                .expectErrorMatches(error -> error.getClass().isInstance(InputValidationException.class));
    }

    @Test
    @DisplayName("Must find square V2 from given input")
    void findSquareV2() {
        Integer input = 10;

        StepVerifier.create(mathService.findSquareV2(input))
                .expectNextMatches(element -> Objects.requireNonNull(element.getBody()).getOutput().equals(input * input))
                .verifyComplete();
    }

    @Test
    @DisplayName("Must handle input exception on square V2 method")
    void findSquareV2Exception() {
        StepVerifier.create(mathService.findSquareV2(-2))
                .expectNextCount(0L)
                .expectErrorMatches(error -> error.getClass().isInstance(InputValidationException.class));
    }

    @Test
    @DisplayName("Must handle multiplication table from given input")
    void multiplicationTable() {
        StepVerifier.create(mathService.multiplicationTable(10))
                .expectNextCount(10L)
                .verifyComplete();
    }

    @Test
    @DisplayName("Must multiply given elements")
    void multiply() {
        MultiplyRequestDto dto = new MultiplyRequestDto(5, 3);
        Mono<MultiplyRequestDto> request = Mono.fromSupplier(() -> dto);

        StepVerifier.create(mathService.multiply(request))
                .expectNextMatches(result -> result.getOutput().equals(dto.getFirst() * dto.getSecond()))
                .verifyComplete();
    }
}