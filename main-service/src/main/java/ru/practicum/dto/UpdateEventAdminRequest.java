package ru.practicum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.practicum.util.EventStateAction;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventAdminRequest {

    @Length(min = 3, max = 120)
    private String title;

    @Length(min = 20, max = 2000)
    private String annotation;

    @Length(min = 20, max = 7000)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private LocalDateTime eventDate;

    @PositiveOrZero
    private Integer participantLimit;

    private Long category;
    private LocationDto location;
    private Boolean paid;
    private Boolean requestModeration;
    private EventStateAction stateAction;
}
