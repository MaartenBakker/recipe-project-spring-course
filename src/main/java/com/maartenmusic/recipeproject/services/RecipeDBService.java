package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.RecipeCommand;
import com.maartenmusic.recipeproject.converters.RecipeCommandToRecipe;
import com.maartenmusic.recipeproject.converters.RecipeToRecipeCommand;
import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.exceptions.NotFoundException;
import com.maartenmusic.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeDBService implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeDBService(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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
        log.debug("I'm in the RecipeDBService");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isEmpty()) {
            throw new NotFoundException("Cannot find recipe with ID: " + id);
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Recipe recipe = findById(id);
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
        log.debug("Deleted RecipeId: " + id);
    }
}
