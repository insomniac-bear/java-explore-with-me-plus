package ru.practicum.service;

import ru.practicum.dto.CompilationDto;
import ru.practicum.dto.NewCompilationDto;
import ru.practicum.dto.UpdateCompilationRequest;

public interface CompilationService {
    CompilationDto add(NewCompilationDto dto);

    void delete(Long id);

    CompilationDto update(Long id, UpdateCompilationRequest dto);
}
