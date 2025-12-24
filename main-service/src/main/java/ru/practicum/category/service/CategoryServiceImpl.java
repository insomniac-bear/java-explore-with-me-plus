package ru.practicum.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.repository.CategoryRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto add(CategoryDto dto) {
        Category entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Category with id=" + id + " was not found");
        }
        repository.deleteById(id);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id=" + id + " was not found"));

        category.setName(dto.getName());

        try {
            return mapper.toDto(repository.save(category));
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }
}
