package ru.flow.imageservice.exception;

public class HttpClientException extends RuntimeException {
    public HttpClientException(String message) {
        super(message);
    }
}
