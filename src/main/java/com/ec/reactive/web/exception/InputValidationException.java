package com.ec.reactive.web.exception;

public class InputValidationException extends RuntimeException {

    private static final String MESSAGE = "Allowed range is 10 - 20";
    private static final Integer ERROR_CODE = 100;
    private final Integer input;

    public InputValidationException(Integer input) {
        super(MESSAGE);
        this.input = input;
    }

    public Integer getErrorCode() {
        return ERROR_CODE;
    }

    public Integer getInput() {
        return input;
    }
}