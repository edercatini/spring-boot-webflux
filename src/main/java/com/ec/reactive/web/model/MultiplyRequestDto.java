package com.ec.reactive.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class MultiplyRequestDto {

    private Integer first;
    private Integer second;
}