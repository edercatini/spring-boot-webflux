package com.ec.reactive.web.controller.handler;

import com.ec.reactive.web.model.InputFailedValidationResponse;
import com.ec.reactive.web.exception.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public InputFailedValidationResponse handleException(InputValidationException exception) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();

        response.setErrorCode(exception.getErrorCode());
        response.setInput(exception.getInput());
        response.setMessage(exception.getMessage());

        return response;
    }
}