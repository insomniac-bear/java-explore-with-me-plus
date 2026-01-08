package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.model.Category;
import ru.practicum.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto add(CategoryDto dto) {
        log.info("Adding category {}", dto);
        Category entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting category {}", id);
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Category with id=" + id + " was not found");
        }
        repository.deleteById(id);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        log.info("Updating category {}", id);
        Category category = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with id=" + id + " was not found"));

        category.setName(dto.getName());

        try {
            return mapper.toDto(repository.save(category));
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        log.info("Finding categories");
        Page<Category> categoryPage = repository.findAll(pageable);
        return categoryPage.getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long catId) {
        log.info("Finding category with id {}", catId);
        Category category = repository.findById(catId)
                .orElseThrow(() -> new NoSuchElementException("Category with id=" + catId + " was not found"));
        return mapper.toDto(category);
    }
}
