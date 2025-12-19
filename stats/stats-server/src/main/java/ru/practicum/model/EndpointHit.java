package ru.practicum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="endpoint_hit")

public class EndpointHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "app")
    private String app;
    @Column(nullable = false, name = "uri")
    private String uri;
    @Column(nullable = false, name = "user_ip")
    private String ip;
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

}
