package ru.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import ru.practicum.event.model.Event;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "compilations",
        uniqueConstraints = @UniqueConstraint(name = "uq_compilation_name", columnNames = "title"))
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Boolean pinned = false;

    @ManyToMany
    @JoinTable(
            name = "compilation_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;
}
