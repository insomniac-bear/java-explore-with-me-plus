package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.ParticipationRequestDto;

@Repository
public class ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
}
