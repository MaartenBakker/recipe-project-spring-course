package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Recipe> findAll() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
