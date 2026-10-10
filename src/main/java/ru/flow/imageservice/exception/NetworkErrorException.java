package ru.flow.imageservice.exception;

public class NetworkErrorException extends RuntimeException {
    public NetworkErrorException(String message) {
        super(message);
    }
}
