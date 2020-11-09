package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.IngredientCommand;
import com.maartenmusic.recipeproject.converters.IngredientCommandToIngredient;
import com.maartenmusic.recipeproject.converters.IngredientToIngredientCommand;
import com.maartenmusic.recipeproject.domain.Ingredient;
import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.repositories.RecipeRepository;
import com.maartenmusic.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientDBService implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientDBService(RecipeRepository recipeRepository,
                               IngredientToIngredientCommand ingredientToIngredientCommand,
                               IngredientCommandToIngredient ingredientCommandToIngredient,
                               UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (recipeOptional.isEmpty()) {

            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository.findById(ingredientCommand.getId())
                .orElseThrow(() -> new RuntimeException("Uom not found!")));
            } else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));

            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();


            if (savedIngredientOptional.isEmpty()) {

                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUom().getId().equals(ingredientCommand.getUom().getId()))
                        .findFirst();
            }
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }


    }
}
