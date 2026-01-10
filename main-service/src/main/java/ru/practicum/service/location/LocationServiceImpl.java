package ru.practicum.service.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.dto.location.NewLocationDto;
import ru.practicum.dto.location.ShortLocationResponseDto;
import ru.practicum.dto.location.UpdateLocationDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {


    @Override
    public List<LocationResponseDto> findAll(Long userId, Pageable pageable) {
        return List.of();
    }

    @Override
    public LocationResponseDto getById(Long userId, Long locationId) {
        return null;
    }

    @Override
    public List<ShortLocationResponseDto> findAll(Pageable pageable) {
        return List.of();
    }

    @Override
    public ShortLocationResponseDto getById(Long locationId) {
        return null;
    }

    @Override
    public NewLocationDto save(Long userId, NewLocationDto dto) {
        return null;
    }

    @Override
    public LocationResponseDto update(Long userId, Long locationId, UpdateLocationDto dto) {
        return null;
    }

    @Override
    public void delete(Long userId, Long locationId) {

    }
}
