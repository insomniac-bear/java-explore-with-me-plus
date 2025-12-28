package ru.practicum.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.ParticipationRequestDto;

public interface ParticipationRequestService {

    ParticipationRequestDto createRequest(Long userId, Long eventId);


}
