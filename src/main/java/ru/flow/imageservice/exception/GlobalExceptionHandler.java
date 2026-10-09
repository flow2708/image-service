package ru.flow.imageservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.flow.imageservice.dto.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImageSourceTimeoutException.class)
    public ResponseEntity<ApiError> handleTimeout(ImageSourceTimeoutException e) {
        return ResponseEntity.status(1000)
                .body(new ApiError("IMAGE_SOURCE_TIMEOUT", e.getMessage()));
    }

    @ExceptionHandler(ImageSourceUnavailableException.class)
    public ResponseEntity<ApiError> handleSourceUnvailable(ImageSourceUnavailableException e) {
        return ResponseEntity.status(1001)
                .body(new ApiError("IMAGE_SOURCE_UNAVAILABLE", e.getMessage()));
    }

    @ExceptionHandler(ImageTooLargeException.class)
    public ResponseEntity<ApiError> handleTooLargeImage(ImageTooLargeException e) {
        return ResponseEntity.status(1002)
                .body(new ApiError("IMAGE_TOO_LARGE", e.getMessage()));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiError> handleInvalidUrl(InvalidUrlException e) {
        return ResponseEntity.status(1003)
                .body(new ApiError("INVALID_URL", e.getMessage()));
    }
}
