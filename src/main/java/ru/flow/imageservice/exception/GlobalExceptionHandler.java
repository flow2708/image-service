package ru.flow.imageservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.flow.imageservice.dto.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ImageSourceTimeoutException.class)
    public ResponseEntity<ApiError> handleTimeout(ImageSourceTimeoutException e) {
        log.warn("Image source timeout: ", e.getMessage());
        return ResponseEntity.status(1000)
                .body(new ApiError("IMAGE_SOURCE_TIMEOUT", e.getMessage()));
    }

    @ExceptionHandler(ImageSourceUnavailableException.class)
    public ResponseEntity<ApiError> handleSourceUnvailable(ImageSourceUnavailableException e) {
        log.warn("Image source unavailable", e.getMessage());
        return ResponseEntity.status(1001)
                .body(new ApiError("IMAGE_SOURCE_UNAVAILABLE", e.getMessage()));
    }

    @ExceptionHandler(ImageTooLargeException.class)
    public ResponseEntity<ApiError> handleTooLargeImage(ImageTooLargeException e) {
        log.warn("Image too large", e.getMessage());
        return ResponseEntity.status(1002)
                .body(new ApiError("IMAGE_TOO_LARGE", e.getMessage()));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiError> handleInvalidUrl(InvalidUrlException e) {
        log.warn("Invalid url", e.getMessage());
        return ResponseEntity.status(1003)
                .body(new ApiError("INVALID_URL", e.getMessage()));
    }
}
