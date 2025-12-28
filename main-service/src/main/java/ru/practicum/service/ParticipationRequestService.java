package ru.practicum.service;

import ru.practicum.dto.ParticipationRequestDto;

public interface ParticipationRequestService {

    ParticipationRequestDto createRequest(Long userId, Long eventId);


}
