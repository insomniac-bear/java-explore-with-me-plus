package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortEventResponseDto {

    private String annotation;

    private CategoryDto category;

    private LocalDateTime eventDate;

    private UserShortDto initiator;

    private LocationDto location;

    private Boolean paid;

    private String title;

    private Integer confirmedRequests;

    private Long id;

    private Long views;
}
