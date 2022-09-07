package com.ec.reactive.web.service;

import com.ec.reactive.web.exception.InputValidationException;
import com.ec.reactive.web.util.SleepUtil;
import com.ec.reactive.web.model.MultiplyRequestDto;
import com.ec.reactive.web.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@Slf4j
public class MathService {

    private final Predicate<Integer> isValidInput = integer -> (integer >= 1 && integer <= 100);

    public Mono<Response> findSquare(Integer input) {
        return Mono.just(input)
                .filter(isValidInput)
                .switchIfEmpty(Mono.error(new InputValidationException(input)))
                .map(integer -> integer * integer)
                .map(Response::new);
    }

    public Mono<ResponseEntity<Response>> findSquareV2(Integer input) {
        return Mono.just(input)
                .filter(isValidInput)
                .map(integer -> integer * integer)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(new Response(result)))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    public Flux<Response> multiplicationTable(Integer input) {
        return Flux.range(1, 10)
                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> log.info("MathService processing: {}", i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> request) {
        return request
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }
}