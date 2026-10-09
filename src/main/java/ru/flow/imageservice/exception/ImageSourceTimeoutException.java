package ru.flow.imageservice.exception;

public class ImageSourceTimeoutException extends RuntimeException {
    public ImageSourceTimeoutException(String message) {
        super(message);
    }
}
