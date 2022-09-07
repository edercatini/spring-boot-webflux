package com.ec.reactive.web.configuration;

import com.ec.reactive.web.service.MathService;
import com.ec.reactive.web.model.MultiplyRequestDto;
import com.ec.reactive.web.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RequestHandler {

    private final MathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest request) {
        Integer input = Integer.valueOf(request.pathVariable("input"));

        return ServerResponse.ok()
                .body(mathService.findSquare(input), Response.class);
    }

    public Mono<ServerResponse> multiplicationTableHandler(ServerRequest request) {
        Integer input = Integer.valueOf(request.pathVariable("input"));

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(mathService.multiplicationTable(input), Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest request) {
        Mono<MultiplyRequestDto> dto = request.bodyToMono(MultiplyRequestDto.class);

        return ServerResponse.ok()
                .body(mathService.multiply(dto), Response.class);
    }
}