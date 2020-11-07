package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.IngredientCommand;
import com.maartenmusic.recipeproject.domain.Ingredient;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    void deleteById(Long id);

    void save(Ingredient ingredient);
}
