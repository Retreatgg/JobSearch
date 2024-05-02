package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Long getCategoryId(String name) {
        return categoryRepository.findIdByName(name);
    }

    @Override
    public List<CategoryDto> categories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        categories.forEach(e -> {
            categoryDtoList.add(CategoryDto.builder()
                    .name(e.getName())
                    .build());
        });

        return categoryDtoList;
    }

    @Override
    public Long findIdByName(String name) {
        return categoryRepository.findIdByName(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Category is not found"));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cateogry is not found"));
    }
}
