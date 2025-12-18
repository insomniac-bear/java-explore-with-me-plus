package ru.practicum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {

}
