package ru.practicum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUserRequestDto {
    @Email
    @Max(254)
    @Min(6)
    @NotNull
    private String email;

    @Max(250)
    @Min(2)
    @NotNull
    private String name;
}
