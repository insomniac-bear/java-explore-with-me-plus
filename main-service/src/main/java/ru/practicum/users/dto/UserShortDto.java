package ru.practicum.users.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserShortDto {

    @NotNull
    private Long id;

    @Max(250)
    @Min(2)
    @NotNull
    private String name;
}
