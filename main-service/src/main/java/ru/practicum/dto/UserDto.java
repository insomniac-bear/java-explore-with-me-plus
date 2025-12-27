package ru.practicum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserDto {

    private Long id;

    @Email
    @Length(min = 6, max = 255)
    @NotNull
    private String email;

    @Length(min = 2, max = 250)
    @NotBlank
    private String name;
}
