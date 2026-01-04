package ru.practicum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.practicum.util.EventState;
import ru.practicum.util.EventStateAction;

import java.time.LocalDateTime;

import static ru.practicum.util.Patterns.TIMESTAMP_PATTERN;

@Data
public class UpdateEventRequestDto {
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern = TIMESTAMP_PATTERN)
    private LocalDateTime eventDate;
    private LocationDto location;
    private String title;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateAction stateAction;
}
