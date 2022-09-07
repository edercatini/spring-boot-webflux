package com.ec.reactive.web.controller;

import com.ec.reactive.web.model.MultiplyRequestDto;
import com.ec.reactive.web.model.Response;
import com.ec.reactive.web.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;

@RestController
@RequestMapping("math")
@RequiredArgsConstructor
public class MathController {

    private final MathService mathService;

    @GetMapping(value = "square/{input}", produces = APPLICATION_NDJSON_VALUE)
    public Mono<Response> findSquare(@PathVariable Integer input) {
        return mathService.findSquare(input);
    }

    @GetMapping(value = "v2/square/{input}", produces = APPLICATION_NDJSON_VALUE)
    public Mono<ResponseEntity<Response>> findSquareV2(@PathVariable Integer input) {
        return mathService.findSquareV2(input);
    }

    @GetMapping(value = "table/{input}", produces = APPLICATION_NDJSON_VALUE)
    public Flux<Response> multiplicationTable(@PathVariable Integer input) {
        return mathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> request) {
        return mathService.multiply(request);
    }
}