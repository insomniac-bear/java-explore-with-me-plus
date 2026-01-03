package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.AdminEventResponseDto;
import ru.practicum.dto.EventResponseDto;
import ru.practicum.dto.UpdateEventAdminRequest;
import ru.practicum.model.AdminEventParam;
import ru.practicum.model.Category;
import ru.practicum.service.EventService;
import ru.practicum.util.EventState;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Slf4j
public class AdminEventsController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdminEventResponseDto> findAll(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) LocalDateTime rangeStart,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size)
    {
        log.info("Got request for Admin: search events");
        AdminEventParam param = new AdminEventParam(users, states, categories,rangeStart, rangeEnd);
        return eventService.getAdminEvents(param,
                PageRequest.of(from / size, size, Sort.by("id").ascending()));
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public AdminEventResponseDto update(@PathVariable Long eventId,
                                   UpdateEventAdminRequest req) {
        log.info("Got request for Admin: update event");
        return eventService.updateAdminEvent(eventId, req);
    }
}