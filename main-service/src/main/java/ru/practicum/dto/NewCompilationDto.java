package ru.practicum.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCompilationDto {

    @NotEmpty
    private Set<Long> events;

    private Boolean pinned = false;

    @NotBlank
    @Size(max = 50)
    private String title;
}
