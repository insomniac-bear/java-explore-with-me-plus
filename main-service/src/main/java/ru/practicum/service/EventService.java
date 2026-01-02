package ru.practicum.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.*;
import ru.practicum.model.AdminEventParam;

import java.util.List;

public interface EventService {
    EventResponseDto create(Long userId, NewEventRequestDto req);

    List<ShortEventResponseDto> getAll(Long userId, Pageable pageable);

    EventResponseDto get(Long userId, Long eventId);

    EventResponseDto update(Long userId, Long eventId, UpdateEventRequestDto req);

    List<EventResponseDto> getAdminEvents(AdminEventParam param, Pageable pageable);

    EventResponseDto updateAdminEvent(Long eventId, UpdateEventAdminRequest req);
}
