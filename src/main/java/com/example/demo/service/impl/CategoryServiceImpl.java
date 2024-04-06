package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Override
    public List<CategoryDto> categories() {
        List<Category> categories = categoryDao.getCategories();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        categories.forEach(e -> {
            categoryDtoList.add(CategoryDto.builder()
                    .name(e.getName())
                    .build());
        });

        return categoryDtoList;
    }
}
