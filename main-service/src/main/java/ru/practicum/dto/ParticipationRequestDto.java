package ru.practicum.dto;

import ru.practicum.util.ParticipationRequestStatus;

public class ParticipationRequestDto {
    private Long id;
    private String created;
    private Long event;
    private Long requestor;
    private ParticipationRequestStatus status;
}
