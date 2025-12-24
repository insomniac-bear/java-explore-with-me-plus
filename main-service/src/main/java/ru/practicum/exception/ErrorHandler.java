package ru.practicum.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(final Exception e) {
        log.info("500 ()", e.getMessage(), e);
        StringWriter sw = new StringWriter();

        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
        }
        String stackTrace = sw.toString();
        return new ApiError(
                "Internal server Error",
                stackTrace,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "UNHANDLED_EXCEPTION");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("400 {}", e.getMessage(), e);

        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
        }
        return new ApiError(
                "Validation error",
                sw.toString(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                "VALIDATION_FAILED"
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(BadRequestException e) {
        log.info("400 Bad Request: {}", e.getMessage(), e);
        return new ApiError(
                "Bad request",
                "",
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                "BAD_REQUEST"
        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(ValidationException e) {
        log.info("400 Bad Request: {}", e.getMessage(), e);
        return new ApiError(
                "Validation failed",
                "",
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR"
        );
    }
}
