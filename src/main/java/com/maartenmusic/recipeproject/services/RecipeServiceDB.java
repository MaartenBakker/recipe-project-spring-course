package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceDB implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceDB(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe findByDescription(String description) {
        return recipeRepository.findByDescription(description).get();
    }

    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return (List<Recipe>) recipeRepository.findAll();
    }
}
