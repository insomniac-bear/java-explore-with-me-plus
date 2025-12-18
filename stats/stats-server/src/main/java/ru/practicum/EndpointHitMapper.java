package ru.practicum;

import org.springframework.stereotype.Component;

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

    EndpointHit  mapToEndpointHit(EndpointHitDto endpointHitDto) {
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
