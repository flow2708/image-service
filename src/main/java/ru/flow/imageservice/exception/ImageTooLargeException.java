package ru.flow.imageservice.exception;

public class ImageTooLargeException extends RuntimeException{
    public ImageTooLargeException(String message) {
        super(message);
    }
}
