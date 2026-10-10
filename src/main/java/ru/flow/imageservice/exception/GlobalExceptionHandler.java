package ru.flow.imageservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.flow.imageservice.dto.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpClientException.class)
    public ResponseEntity<ApiError> handleHttpClient(HttpClientException e) {
        log.warn("Http client error: ", e.getMessage());
        return ResponseEntity.status(400)
                .body(new ApiError("HTTP_CLIENT_ERROR", e.getMessage()));
    }

    @ExceptionHandler(HttpServerException.class)
    public ResponseEntity<ApiError> handleHttpServer(HttpServerException e) {
        log.warn("Http server error: ", e.getMessage());
        return ResponseEntity.status(500)
                .body(new ApiError("HTTP_SERVER_ERROR", e.getMessage()));
    }

    @ExceptionHandler(ImageSourceTimeoutException.class)
    public ResponseEntity<ApiError> handleTimeout(ImageSourceTimeoutException e) {
        log.warn("Image source timeout: ", e.getMessage());
        return ResponseEntity.status(504)
                .body(new ApiError("IMAGE_SOURCE_TIMEOUT", e.getMessage()));
    }

    @ExceptionHandler(ImageSourceUnavailableException.class)
    public ResponseEntity<ApiError> handleSourceUnvailable(ImageSourceUnavailableException e) {
        log.warn("Image source unavailable: ", e.getMessage());
        return ResponseEntity.status(502)
                .body(new ApiError("IMAGE_SOURCE_UNAVAILABLE", e.getMessage()));
    }

    @ExceptionHandler(ImageTooLargeException.class)
    public ResponseEntity<ApiError> handleTooLargeImage(ImageTooLargeException e) {
        log.warn("Image too large: ", e.getMessage());
        return ResponseEntity.status(413)
                .body(new ApiError("IMAGE_TOO_LARGE", e.getMessage()));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiError> handleInvalidUrl(InvalidUrlException e) {
        log.warn("Invalid url: ", e.getMessage());
        return ResponseEntity.status(400)
                .body(new ApiError("INVALID_URL", e.getMessage()));
    }
    @ExceptionHandler(NetworkErrorException.class)
    public ResponseEntity<ApiError> handleNetworkError(NetworkErrorException e) {
        log.warn("Network error: ", e.getMessage());
        return ResponseEntity.status(599)
                .body(new ApiError("NETWORK_ERROR", e.getMessage()));
    }
}
