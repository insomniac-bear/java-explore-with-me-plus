package ru.practicum.service.location;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.dto.location.NewLocationDto;
import ru.practicum.dto.location.ShortLocationResponseDto;
import ru.practicum.dto.location.UpdateLocationDto;

import java.util.List;

public interface LocationService {

    List<LocationResponseDto> findAll(Long userId, Pageable pageable);

    LocationResponseDto getById(Long userId, Long locationId);

    List<ShortLocationResponseDto> findAll(Pageable pageable);

    ShortLocationResponseDto getById(Long locationId);

    NewLocationDto save(Long userId, NewLocationDto dto);

    LocationResponseDto update(Long userId, Long locationId, UpdateLocationDto dto);

    void delete(Long userId, Long locationId);
}
