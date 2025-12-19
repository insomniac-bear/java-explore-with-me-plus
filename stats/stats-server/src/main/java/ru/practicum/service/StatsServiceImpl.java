package ru.practicum.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointHitDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.EndpointHitMapper;
import ru.practicum.repository.StatsRepository;

@Service
@Slf4j
@AllArgsConstructor

public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;
    private final EndpointHitMapper endpointHitMapper;

    @Override
    public EndpointHitDto addHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = endpointHitMapper.mapToEndpointHit(endpointHitDto);
        return endpointHitMapper.mapToEndpointHitDto(statsRepository.save(endpointHit));
    }
}
