package ru.practicum.stats.client;

import ru.practicum.stats.dto.EndpointHitDto;
import ru.practicum.stats.dto.ViewStatsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class StatsClientImpl implements StatsClient {
    private final RestTemplate restTemplate;

    public StatsClientImpl(@Value("${stats-server.url}") String serverUrl,
            RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build();
    }

    @Override
    public void hit(EndpointHitDto hit) {
        restTemplate.postForLocation("/hit", hit);
    }

    @Override
    public List<ViewStatsDto> getStats(
            String start,
            String end,
            List<String> uris,
            boolean unique
    ) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/stats")
                .queryParam("start", encode(start))
                .queryParam("end", encode(end))
                .queryParam("unique", unique);

        if (uris != null && !uris.isEmpty()) {
            uris.forEach(uri -> uriBuilder.queryParam("uris", uri));
        }

        ResponseEntity<ViewStatsDto[]> response =
                restTemplate.getForEntity(
                        uriBuilder.toUriString(),
                        ViewStatsDto[].class
                );

        return response.getBody() == null
                ? List.of()
                : Arrays.asList(response.getBody());
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
