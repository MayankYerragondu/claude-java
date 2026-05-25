package com.example.caludetestapp.exception;

public class TestException extends RuntimeException {

    private final int statusCode;

    public TestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() { return statusCode; }
}
