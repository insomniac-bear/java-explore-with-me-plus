package ru.practicum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.util.EventState;

import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "public")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    @NotBlank
    @Length(min = 3, max = 120)
    private String title;

    @Column(nullable = false, length = 7000)
    @NotBlank
    @Length(min = 20, max = 7000)
    private String description;

    @Column(nullable = false, length = 2000)
    @NotBlank
    @Length(min = 20, max = 2000)
    private String annotation;

    @Column(name = "event_date", nullable = false)
    @NotNull
    @Future
    private LocalDateTime eventDate;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean paid = false;

    @Column(name = "participant_limit", columnDefinition = "INT DEFAULT 0")
    @PositiveOrZero
    private Integer participantLimit = 0;

    @Column(name = "request_moderation", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean requestModeration = false;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NotNull
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private EventState state = EventState.PENDING;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User initiator;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "confirmed_requests")
    private Integer confirmedRequests = 0;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", state=" + state +
                ", location=" + (location != null ? location.getName() : "null") +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }
}
