package ru.practicum.service;

import ru.practicum.EndpointHitDto;

public interface StatsService {
    EndpointHitDto addHit(EndpointHitDto endpointHitDto);

}
