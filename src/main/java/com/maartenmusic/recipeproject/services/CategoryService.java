package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Category;

public interface CategoryService {

    Category findByDescription(String description);
}
