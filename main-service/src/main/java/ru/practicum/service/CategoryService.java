package ru.practicum.service;

import ru.practicum.dto.CategoryDto;

public interface CategoryService {
    CategoryDto add(CategoryDto dto);

    void delete(Long id);

    CategoryDto update(Long id, CategoryDto dto);
}
