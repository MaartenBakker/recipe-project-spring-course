package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Recipe findByDescription(String description);

    void save(Recipe recipe);

    Set<Recipe> findAll();

}
