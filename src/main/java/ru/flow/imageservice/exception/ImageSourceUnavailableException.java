package ru.flow.imageservice.exception;

public class ImageSourceUnavailableException extends RuntimeException {
    public ImageSourceUnavailableException(String message) {
        super(message);
    }
}
