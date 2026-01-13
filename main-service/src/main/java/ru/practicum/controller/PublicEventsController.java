package ru.practicum.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.event.EventResponseDto;
import ru.practicum.dto.event.EventSearchCriteria;
import ru.practicum.dto.event.ShortEventResponseDto;
import ru.practicum.service.event.EventService;
import ru.practicum.stats.client.StatsClient;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class PublicEventsController {

    private static final String MAIN_SERVICE = "ewm-main-service";

    private final EventService service;
    private final StatsClient statsClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShortEventResponseDto> findAll(@ModelAttribute EventSearchCriteria criteria, HttpServletRequest req) throws Exception {
       log.info("Find all events");
       List<ShortEventResponseDto> res = service.find(criteria);
       saveHit(req);
       return res;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDto getEvent(@PathVariable Long id, HttpServletRequest req) {
        log.info("Get event by id {}", id);
        EventResponseDto res = service.get(id);
        saveHit(req);
        return res;
    }

    //!!!!!!!!FEATURE - 3 ЗАДАНИЕ
    @GetMapping("/location/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortEventResponseDto> findEventsByLocation(@PathVariable Long locationId,
                                                      @RequestParam(defaultValue = "0") Integer from,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      HttpServletRequest req) {

        log.info("Find events by location {}", locationId);
        List <ShortEventResponseDto> events = service.findByLocation(locationId, PageRequest.of(from / size, size, Sort.by("id").ascending()));
        saveHit(req);
        return events;
    }

    @GetMapping("/near")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortEventResponseDto> findEventsNear(@RequestParam Double lat,
                                           @RequestParam Double lon,
                                           @RequestParam(defaultValue = "5.0") Double radius,
                                           @RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size) {

        return service.findEventsNear(lat, lon, radius,
                PageRequest.of(from / size, size, Sort.by("id").ascending()));
    }
    //!!!!!!!!FEATURE - 3 ЗАДАНИЕ


    private void saveHit(HttpServletRequest request) {
        EndpointHitDto endpointHitDto = new EndpointHitDto();
        endpointHitDto.setApp(MAIN_SERVICE);
        endpointHitDto.setUri(request.getRequestURI());
        endpointHitDto.setIp(request.getRemoteAddr());
        endpointHitDto.setTimestamp(LocalDateTime.now());
        statsClient.hit(endpointHitDto);
    }
}
