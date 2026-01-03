package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import ru.practicum.dto.*;
import ru.practicum.error.ConflictException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.AdminEventParam;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.User;
import ru.practicum.repository.CategoryRepository;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.UserRepository;
import ru.practicum.util.EventState;
import ru.practicum.util.EventStateAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
                        return new NoSuchElementException("Category with id " + req.getCategory() + " notFound");
                    });
        }

        Event updatingEvent = mapper.updateEventField(event, req, category);
        log.info("Сорбытие {} обновлено данными из запроса {}", updatingEvent, req);

        return mapper.eventToEventResponseDto(updatingEvent, user);
    }

    @Override
    public List<AdminEventResponseDto> getAdminEvents(AdminEventParam param, Pageable pageable) {

        return eventRepository.getAdminEvents(param, pageable).stream()
                .map(mapper::toAdminEventFullDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminEventResponseDto updateAdminEvent(Long eventId, UpdateEventAdminRequest req) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("Event with id" + eventId + "not found"));
Event updatedEvent = updateEventByAdmin(event, req);
        return mapper.toAdminEventFullDto(eventRepository.save(updatedEvent));
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new NoSuchElementException("User with id " + userId + " notFound");
                });
    }

    private Event findEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    return new NoSuchElementException("Event with id " + eventId + " notFound");
                });
    }

    private void checkPermission(Event event, User user) {
        if (!event.getInitiator().equals(user)) {
            throw new ResourceAccessException("Access to event " + event + " forbidden");
        }
    }

    private Event updateEventByAdmin(Event event, UpdateEventAdminRequest update) {

        if (update.getCategory() != null) {
            Category category = categoryRepository.findById(Long.valueOf(update.getCategory()))
                    .orElseThrow(() -> new NoSuchElementException("Category with id " + update.getCategory() + " doesnt exist "));
            event.setCategory(category);
        }

        EventState state = event.getState();
        EventStateAction updateStateAction = update.getStateAction();
        if (updateStateAction != null) {

            if (updateStateAction == EventStateAction.PUBLISH_EVENT) {
                if (state != EventState.WAITING) {
                    throw new ConflictException("Only events with waiting status could be published");
                }
                if (event.getEventDate().minusHours(1L).isBefore(LocalDateTime.now())) {
                    throw new ConflictException("Event could be changed only one hour before now");
                }
                event.setState(EventState.PUBLISHED);
                event.setPublishedOn(LocalDateTime.now());

            } else if (updateStateAction == EventStateAction.REJECT_EVENT) {
                if (state == EventState.PUBLISHED) {
                    throw new ConflictException("Published event could not be rejected");
                }
                event.setState(EventState.REJECTED);

            } else {
                throw new NoSuchElementException("Unknown state action");
            }
        }

        if (update.getTitle() != null) {
            event.setTitle(update.getTitle());
        }

        if (update.getAnnotation() != null) {
            event.setAnnotation(update.getAnnotation());
        }

        if (update.getDescription() != null) {
            event.setDescription(update.getDescription());
        }

        if (update.getEventDate() != null) {
            if (update.getEventDate().isBefore(LocalDateTime.now())) {
                throw new ConflictException("Event date couldnt be in the past");
            }
            event.setEventDate(update.getEventDate());
        }

        if (update.getParticipantLimit() != null) {
            event.setParticipantLimit(update.getParticipantLimit());
        }

        if (update.getLocation() != null) {
            event.setLat(update.getLocation().getLat());
            event.setLon(update.getLocation().getLat());
        }

        if (update.getPaid() != null) {
            event.setPaid(update.getPaid());
        }

        if (update.getRequestModeration() != null) {
            event.setRequestModeration(update.getRequestModeration());
        }

        return event;
    }
}
