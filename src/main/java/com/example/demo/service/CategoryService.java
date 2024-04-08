package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Long getCategoryId(String name);
    List<CategoryDto> categories();
}
