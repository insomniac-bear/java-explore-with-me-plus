package ru.practicum.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ApiError {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private String description;
    private String stackTrace;
    private String message;
    private String reason;
    private HttpStatus status;
    private String timestamp;

    public ApiError(String description, String stackTrace, String message,
                    HttpStatus status, String reason) {
        this.description = description;
        this.stackTrace = stackTrace;
        this.message = message;
        this.status = status;
        this.reason = reason;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));
    }

}
