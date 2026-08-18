package com.pm.patientservice.exceptions;

public class CustomRequestValidationException extends RuntimeException {

    public CustomRequestValidationException(String message) {
        super(message);
    }
}