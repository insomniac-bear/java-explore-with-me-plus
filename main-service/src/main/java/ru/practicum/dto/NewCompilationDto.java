package ru.practicum.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class NewCompilationDto {

    @NotEmpty
    private Set<Long> events;

    private Boolean pinned = false;

    @NotBlank
    private String title;
}
