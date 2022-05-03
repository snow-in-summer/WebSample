package com.example.websample.exception;

import com.example.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessException.class)
    public ErrorResponse handleIllegalAccessException(IllegalAccessException e) {
        log.error("Illegal Exception : ", e);

        return new ErrorResponse("ACCESS_DENIED", "Illegal Exception occurred.");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebSampleException.class)
    public ErrorResponse handleWebSampleException(WebSampleException e) {
        log.error("WebSampleException Exception : ", e);

        return new ErrorResponse("BAD_REQUEST", "WebSampleException Exception occurred.");
    }

    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException e) {
        log.error("INSUFFICIENT_STORAGE Exception : ", e);

        return new ErrorResponse("INSUFFICIENT_STORAGE", "RuntimeException Exception occurred.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Internal Server Exception : ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "INTERNAL_SERVER_ERROR",
                        "Illegal Exception occurred."
                ));
    }
}
