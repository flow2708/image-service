package ru.flow.imageservice.exception;

public class HttpServerException extends RuntimeException {
    public HttpServerException(String message) {
        super(message);
    }
}
