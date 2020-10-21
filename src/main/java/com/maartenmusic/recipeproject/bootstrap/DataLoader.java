package com.maartenmusic.recipeproject.bootstrap;

import com.maartenmusic.recipeproject.domain.Category;
import com.maartenmusic.recipeproject.domain.Difficulty;
import com.maartenmusic.recipeproject.domain.Ingredient;
import com.maartenmusic.recipeproject.domain.Recipe;
import com.maartenmusic.recipeproject.services.CategoryService;
import com.maartenmusic.recipeproject.services.RecipeService;
import com.maartenmusic.recipeproject.services.UnitOfMeasureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryService categoryService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public DataLoader(CategoryService categoryService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public void run(String... args) throws Exception {
        getRecipes();

    }

    private void getRecipes() {
        Recipe perfectGuacamole = new Recipe();
        Category mexican = categoryService.findByDescription("Mexican");

        perfectGuacamole.getCategories().add(mexican);

        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setDifficulty(Difficulty.MODERATE);

        Ingredient avocados = new Ingredient();
        avocados.setAmount(new BigDecimal(2));
        avocados.setDescription("Avocados");
        avocados.setRecipe(perfectGuacamole);

        Ingredient salt = new Ingredient();
        salt.setAmount(new BigDecimal(1/4));
        salt.setDescription("Salt");
        salt.setRecipe(perfectGuacamole);
        salt.setUom(unitOfMeasureService.findByDescription("Teaspoon"));

        Ingredient limeJuice = new Ingredient();
        limeJuice.setAmount(new BigDecimal(1));
        limeJuice.setDescription("Lime juice");
        limeJuice.setRecipe(perfectGuacamole);
        limeJuice.setUom(unitOfMeasureService.findByDescription("Tablespoon"));

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(avocados);
        ingredientSet.add(salt);
        ingredientSet.add(limeJuice);

        perfectGuacamole.setIngredients(ingredientSet);

        recipeService.saveRecipe(perfectGuacamole);
    }
}
