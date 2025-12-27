package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.CompilationDto;
import ru.practicum.model.Compilation;
import ru.practicum.event.mapper.EventMapper;

@Mapper(componentModel = "spring", uses = EventMapper.class)
public interface CompilationMapper {

    @Mapping(target = "events", source = "events")
    CompilationDto toDto(Compilation entity);
}
