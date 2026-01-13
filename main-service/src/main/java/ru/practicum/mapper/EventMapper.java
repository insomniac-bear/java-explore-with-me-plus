package ru.practicum.mapper;

import org.mapstruct.*;
import ru.practicum.dto.event.*;
import ru.practicum.model.Category;
import ru.practicum.model.Event;
import ru.practicum.model.Location;
import ru.practicum.model.User;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, UserMapper.class, LocationMapper.class})
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", expression = "java(ru.practicum.util.EventState.PENDING)")
    @Mapping(target = "createdOn", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "initiator", source = "user")
    Event eventRequestToEvent(NewEventRequestDto eventRequestDto, Category category, User user, Location location);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", ignore = true)
    Event updateEventField(@MappingTarget Event event, UpdateEventRequestDto req, Category category);

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "location", source = "event.location", qualifiedByName = "toShortResponseDto")
    @Mapping(target = "initiator", source = "user")
    ShortEventResponseDto eventToShortEventResponseDto(Event event, User user);

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "location", source = "event.location", qualifiedByName = "toShortResponseDto")
    @Mapping(target = "initiator", source = "event.initiator")
    ShortEventResponseDto eventToShortEventResponseDto(Event event);

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "location", source = "event.location")
    @Mapping(target = "initiator", source = "user")
    EventResponseDto eventToEventResponseDto(Event event, User user);

    @Mapping(target = "location", source = "event.location")
    @Mapping(target = "initiator", source = "event.initiator")
    AdminEventResponseDto toAdminEventFullDto(Event event);
}
