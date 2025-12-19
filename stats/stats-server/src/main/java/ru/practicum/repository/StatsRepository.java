package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ViewStatsDto;
import ru.practicum.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT NEW ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(h)) " +
            "FROM EndpointHit h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<ViewStatsDto> getNotUniqueStats(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<ViewStatsDto> getUniqueStats(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<ViewStatsDto> getUniqueStatsWithUris(LocalDateTime startTime, LocalDateTime endTime, List<String> uris);

    @Query("SELECT NEW ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(h)) " +
            "FROM EndpointHit h " +
            "WHERE h.timestamp BETWEEN ?1 AND ?2 " +
            "AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h) DESC")
    List<ViewStatsDto> getNotUniqueStatsWithUris(LocalDateTime startTime, LocalDateTime endTime, List<String> uris);

}
