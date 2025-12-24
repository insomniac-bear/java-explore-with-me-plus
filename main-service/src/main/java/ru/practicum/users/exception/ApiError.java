package ru.practicum.users.exception;

import java.util.List;

public class ApiError {

    String description;
    List<String> errors;
    String message;
    String reason;
    ErrorStatus status;
    String timestamp;

}
