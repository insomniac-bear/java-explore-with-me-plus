package ru.practicum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserShortDto {

    @NotNull
    private Long id;

    @Length(min = 2, max = 250)
    @NotBlank
    private String name;
}