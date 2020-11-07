package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.IngredientCommand;
import com.maartenmusic.recipeproject.converters.IngredientToIngredientCommand;
import com.maartenmusic.recipeproject.domain.Ingredient;
import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.repositories.IngredientRepository;
import com.maartenmusic.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class IngredientDBService implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientDBService(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);


        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();

            Set<Ingredient> ingredients = recipe.getIngredients();

            Ingredient foundIngredient = ingredients.stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst().orElse(null);

            return ingredientToIngredientCommand.convert(foundIngredient);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(Ingredient ingredient) {

    }
}
