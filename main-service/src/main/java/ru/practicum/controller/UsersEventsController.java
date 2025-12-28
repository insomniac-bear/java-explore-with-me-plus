package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.EventResponseDto;
import ru.practicum.dto.NewEventRequestDto;
import ru.practicum.dto.ShortEventResponseDto;
import ru.practicum.dto.UpdateEventRequestDto;
import ru.practicum.service.EventService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class UsersEventsController {
    private final EventService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDto create(@PathVariable Long userId, @Valid @RequestBody NewEventRequestDto req) {
        log.info("POST /users/{}/events - запрос на создание события: {}", userId, req);
        return service.create(userId, req);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDto get(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("GET /users/{}/events/{} - запрос на получение события", userId, eventId);
        return service.get(userId, eventId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShortEventResponseDto> getAll(@PathVariable Long userId,
                                              @RequestParam (defaultValue = "0") int from,
                                              @RequestParam (defaultValue = "10") int size) {
        log.info("GET /users/{}/events - запрос на получение всех событий", userId);
        return service.getAll(userId, PageRequest.of(from / size, size));
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDto update(@PathVariable Long userId,
                                   @PathVariable Long eventId,
                                   @Valid @RequestBody UpdateEventRequestDto req) {
        log.info("GET /users/{}/events/{} - запрос на обновление события {}", userId, eventId, req);
        return service.update(userId, eventId, req);
    }
}
