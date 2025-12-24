package ru.practicum.users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Max(254)
    @Min(6)
    @NotNull
    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Max(250)
    @Min(2)
    @NotNull
    @Column(nullable = false, name = "name")
    private String name;
}
