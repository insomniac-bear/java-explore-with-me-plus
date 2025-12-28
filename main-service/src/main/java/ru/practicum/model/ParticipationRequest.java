package ru.practicum.model;

import ru.practicum.util.ParticipationRequestStatus;

public class ParticipationRequest {
    private Long id;
    private String created;
    private Long event;
    private Long requestor;
    private ParticipationRequestStatus status;
}
