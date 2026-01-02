package ru.practicum.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.dto.EventResponseDto;
import ru.practicum.dto.NewEventRequestDto;
import ru.practicum.dto.ShortEventResponseDto;
import ru.practicum.dto.UpdateEventRequestDto;
import ru.practicum.model.Category;
import ru.practicum.util.EventState;

import java.sql.Timestamp;
import java.util.List;

public interface EventService {
    EventResponseDto create(Long userId, NewEventRequestDto req);

    List<ShortEventResponseDto> getAll(Long userId, Pageable pageable);

    EventResponseDto get(Long userId, Long eventId);

    EventResponseDto update(Long userId, Long eventId, UpdateEventRequestDto req);

    List<EventResponseDto> getAdminEvents(List<Integer> usersIds, List<EventState> states, List<Category> categories, Timestamp rangeStart, Timestamp rangeEnd, PageRequest id);
}
