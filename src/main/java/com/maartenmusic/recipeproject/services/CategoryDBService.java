package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Category;
import com.maartenmusic.recipeproject.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CategoryDBService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDBService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByDescription(String description) {
        return categoryRepository.findByDescription(description).get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
