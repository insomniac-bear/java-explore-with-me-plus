package ru.practicum.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserShortDto {

    @NotNull
    private Long id;

    @Length(min = 2, max = 250)
    @NotBlank
    private String name;
}