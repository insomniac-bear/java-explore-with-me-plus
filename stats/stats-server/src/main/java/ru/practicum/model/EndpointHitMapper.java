package ru.practicum.model;

import org.springframework.stereotype.Component;
import ru.practicum.EndpointHitDto;

@Component
public class EndpointHitMapper {

    public EndpointHitDto mapToEndpointHitDto(EndpointHit endpointHit) {

        EndpointHitDto endpointHitDto = EndpointHitDto.builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getTimestamp())
                .build();
        return endpointHitDto;
    }

    public EndpointHit  mapToEndpointHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = EndpointHit.builder()
                .id(endpointHitDto.getId())
                .app(endpointHitDto.getApp())
                .uri(endpointHitDto.getUri())
                .ip(endpointHitDto.getIp())
                .timestamp(endpointHitDto.getTimestamp())
                .build();
        return endpointHit;
    }

}
