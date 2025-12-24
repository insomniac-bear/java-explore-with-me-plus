package ru.practicum.users.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ApiError {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    String description;
    List<String> errors;
    String message;
    String reason;
    String status;
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));

}
