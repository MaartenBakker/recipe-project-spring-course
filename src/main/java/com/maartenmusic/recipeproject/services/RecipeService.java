package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe findByDescription(String description);

    void saveRecipe(Recipe recipe);

    List<Recipe> findAll();

}
