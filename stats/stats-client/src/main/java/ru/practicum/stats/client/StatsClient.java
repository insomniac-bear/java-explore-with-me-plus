package ru.practicum.stats.client;

import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.ViewStatsDto;

import java.util.List;

public interface StatsClient {
    void hit(EndpointHitDto hit);

    List<ViewStatsDto> getStats(
            String start,
            String end,
            List<String> uris,
            boolean unique
    );
}
