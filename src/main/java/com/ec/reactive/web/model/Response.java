package com.ec.reactive.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class Response {

    private Date date = new Date();
    private Integer output;

    public Response(Integer output) {
        this.output = output;
    }
}