package ru.practicum.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EventRequestStatusUpdateResult {

    @Builder.Default
    private List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
    @Builder.Default
    private List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
}
