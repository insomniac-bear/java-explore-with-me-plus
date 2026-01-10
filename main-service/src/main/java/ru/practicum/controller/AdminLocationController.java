package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.dto.location.NewLocationDto;
import ru.practicum.dto.location.UpdateLocationDto;
import ru.practicum.service.location.LocationService;

import java.util.List;

@RestController
@RequestMapping("/admin/locations")
@RequiredArgsConstructor
@Slf4j
public class AdminLocationController {

    private final LocationService locationService;

    @GetMapping
    public List<LocationResponseDto> findAll(@PathVariable Long userId,
                                             @RequestParam(defaultValue = "0") Integer from,
                                             @RequestParam(defaultValue = "10") Integer size) {
        log.info("Find all locations");
        return locationService.findAll(userId, PageRequest.of(from / size, size, Sort.by("id").ascending()));
    }

    @GetMapping("/{locationId}")
    public LocationResponseDto findOne(@PathVariable Long locationId,
                                       @PathVariable Long userId) {
        log.info("Find location with id {}", locationId);
        return locationService.getById(locationId, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public NewLocationDto save(@PathVariable Long userId, NewLocationDto dto) {
        log.info("Save location {}", dto);
        return locationService.save(userId, dto);
    }

    @PatchMapping("/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public LocationResponseDto update(@PathVariable Long userId,
                                      @PathVariable Long locationId,
                                      @RequestBody UpdateLocationDto dto) {
        log.info("Update location {}", dto);
        return locationService.update(userId, locationId, dto);
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId,
                       @PathVariable Long locationId) {
        log.info("Delete location {}", locationId);
        locationService.delete(userId, locationId);
    }

}
