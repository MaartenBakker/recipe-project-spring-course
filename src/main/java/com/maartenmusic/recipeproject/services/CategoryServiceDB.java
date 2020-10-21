package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Category;
import com.maartenmusic.recipeproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceDB implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceDB(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByDescription(String description) {
        return categoryRepository.findByDescription(description).get();
    }
}
