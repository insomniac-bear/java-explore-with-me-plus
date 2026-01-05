package ru.practicum.service.event;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.*;
import ru.practicum.util.EventStateAction;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventResponseDto create(Long userId, NewEventRequestDto req);

    List<ShortEventResponseDto> find(EventSearchCriteria criteria);

    List<ShortEventResponseDto> getAll(Long userId, Pageable pageable);

    ShortEventResponseDto get(Long eventId);

    EventResponseDto get(Long userId, Long eventId);

    EventResponseDto update(Long userId, Long eventId, UpdateEventRequestDto req);

    AdminEventResponseDto updateAdminEvent(Long eventId, UpdateEventAdminRequest req);

    List<AdminEventResponseDto> findAdminEvents(List<Long> users, List<EventStateAction> states,
                                                List<Long> categories, LocalDateTime rangeStart,
                                                LocalDateTime rangeEnd, Pageable pageable);
}
