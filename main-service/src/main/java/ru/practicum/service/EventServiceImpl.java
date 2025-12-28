package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import ru.practicum.dto.EventResponseDto;
import ru.practicum.dto.NewEventRequestDto;
import ru.practicum.dto.ShortEventResponseDto;
import ru.practicum.dto.UpdateEventRequestDto;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.User;
import ru.practicum.repository.CategoryRepository;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {
    private final EventMapper mapper;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public EventResponseDto create(Long userId, NewEventRequestDto req) {
        User user = findUser(userId);

        Category category = categoryRepository.findById(req.getCategory())
                .orElseThrow(() -> {
                    log.error("Попытка создания события {} с несуществующей категорией с id {}", req, req.getCategory());
                    return new NoSuchElementException("Category with id " + req.getCategory() + " notFound");
                });

        Event newEvent = mapper.eventRequestToEvent(req, category, user);

        Event savedEvent = eventRepository.save(newEvent);
        log.info("Создано новое событие {} от пользователя {}", savedEvent, user);

        return mapper.eventToEventResponseDto(savedEvent, user);
    }

    @Override
    public EventResponseDto get(Long userId, Long eventId) {
        User user = findUser(userId);
        Event event = findEvent(eventId);

        log.info("Найдено событие {}", event);
        checkPermission(event, user);

        return mapper.eventToEventResponseDto(event, user);
    }

    @Override
    public List<ShortEventResponseDto> getAll(Long userId, Pageable pageable) {
        User user = findUser(userId);

        return eventRepository.findAllByInitiatorId(userId, pageable)
                .stream()
                .map((event) -> mapper.eventToShortEventResponseDto(event, user))
                .toList();
    }

    @Override
    @Transactional
    public EventResponseDto update(Long userId, Long eventId, UpdateEventRequestDto req) {
        User user = findUser(userId);
        Event event = findEvent(eventId);
        checkPermission(event, user);
        Category category = null;

        if (req.getCategory() != null) {
            category = categoryRepository.findById(req.getCategory())
                    .orElseThrow(() -> {
                        log.error("Попытка обновления события {} с несуществующей категорией с id {}", req, req.getCategory());
                        return new NoSuchElementException("Category with id " + req.getCategory() + " notFound");
                    });
        }

        Event updatingEvent = mapper.updateEventField(event, req, category);
        log.info("Сорбытие {} обновлено данными из запроса {}", updatingEvent, req);

        return mapper.eventToEventResponseDto(updatingEvent, user);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Попытка найти несуществующего пользователя с id {}", userId);
                    return new NoSuchElementException("User with id " + userId + " notFound");
                });
    }

    private Event findEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.error("Попытка найти несуществующее событие с id {}", eventId);
                    return new NoSuchElementException("Event with id " + eventId + " notFound");
                });
    }

    private void checkPermission(Event event, User user) {
        if (!event.getInitiator().equals(user)) {
            log.error("Попытка доступа пользователя {} к чужому событию {}", user, event);
            throw new ResourceAccessException("Access to event " + event + " forbidden");
        }
    }
}
