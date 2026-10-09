package ru.flow.imageservice.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.flow.imageservice.dto.ApiError;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ImageSourceTimeoutException.class)
    public ResponseEntity<ApiError> handleTimeout(ImageSourceTimeoutException e) {
        log.warning("Image source timeout");
        return ResponseEntity.status(1000)
                .body(new ApiError("IMAGE_SOURCE_TIMEOUT", e.getMessage()));
    }

    @ExceptionHandler(ImageSourceUnavailableException.class)
    public ResponseEntity<ApiError> handleSourceUnvailable(ImageSourceUnavailableException e) {
        log.warning("Image source unavailable");
        return ResponseEntity.status(1001)
                .body(new ApiError("IMAGE_SOURCE_UNAVAILABLE", e.getMessage()));
    }

    @ExceptionHandler(ImageTooLargeException.class)
    public ResponseEntity<ApiError> handleTooLargeImage(ImageTooLargeException e) {
        log.warning("Image too large");
        return ResponseEntity.status(1002)
                .body(new ApiError("IMAGE_TOO_LARGE", e.getMessage()));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiError> handleInvalidUrl(InvalidUrlException e) {
        log.warning("Invalid url");
        return ResponseEntity.status(1003)
                .body(new ApiError("INVALID_URL", e.getMessage()));
    }
}
