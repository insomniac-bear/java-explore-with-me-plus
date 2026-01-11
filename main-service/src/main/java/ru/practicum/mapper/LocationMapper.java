package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.dto.location.NewLocationDto;
import ru.practicum.dto.location.ShortLocationResponseDto;
import ru.practicum.dto.location.UpdateLocationDto;
import ru.practicum.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationResponseDto toFullResponseDto(Location location);

    ShortLocationResponseDto toShortResponseDto(Location location);

    Location toLocation(NewLocationDto dto);

    void updateFromDto(UpdateLocationDto dto, Location existingLocation);
}
