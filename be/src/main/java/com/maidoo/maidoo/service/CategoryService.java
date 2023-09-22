package com.maidoo.maidoo.service;


import com.maidoo.maidoo.domain.Category;
import com.maidoo.maidoo.repository.ICategoryRepository;
import com.maidoo.maidoo.service.dto.CategoryDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ICategoryRepository iCategoryRepository;


    public List<Category> getAllCategory() {
        return this.iCategoryRepository.findAll();
    }

    public CategoryDTO saveCategory(CategoryDTO dto) {
        Category category = MapperUtils.map(dto, Category.class);
        this.iCategoryRepository.save(category);
        return MapperUtils.map(category, CategoryDTO.class);
    }

    public CategoryDTO getCategory(Long idCategory) {
        Category category = this.iCategoryRepository.findById(idCategory).orElseThrow();
        return MapperUtils.map(category, CategoryDTO.class);
    }
}
