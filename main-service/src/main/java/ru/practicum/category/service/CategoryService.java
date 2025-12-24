package ru.practicum.category.service;

import ru.practicum.category.dto.CategoryDto;

public interface CategoryService {
    CategoryDto add(CategoryDto dto);

    void delete(Long id);

    CategoryDto update(Long id, CategoryDto dto);
}
