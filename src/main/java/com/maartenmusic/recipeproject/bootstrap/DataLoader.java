package com.maartenmusic.recipeproject.bootstrap;

import com.maartenmusic.recipeproject.domain.*;
import com.maartenmusic.recipeproject.services.CategoryService;
import com.maartenmusic.recipeproject.services.RecipeService;
import com.maartenmusic.recipeproject.services.UnitOfMeasureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private UnitOfMeasure getUnitOfMeasure(String description) {
        UnitOfMeasure uom;
        try {
            uom = unitOfMeasureService.findByDescription(description);
        } catch (Exception e) {
            uom = new UnitOfMeasure(description);
            unitOfMeasureService.save(uom);
        }

        return uom;
    }

    private Category getCategory(String description, Recipe recipe) {
        Category category;
        try {
            category = categoryService.findByDescription(description);

        } catch (Exception e) {
            System.out.println("inside Catch block");

            category = new Category(description);
            categoryService.save(category);
        }

        return category;
    }

    private void getRecipes() {
        Recipe perfectGuacamole = new Recipe();
        Category mexican = getCategory("Mexican", perfectGuacamole);
        perfectGuacamole.getCategories().add(mexican);

        perfectGuacamole.setCookTime(10);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(3);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setDifficulty(Difficulty.MODERATE);

        UnitOfMeasure uomEach = getUnitOfMeasure("Each");
        UnitOfMeasure uomPinch = getUnitOfMeasure("Pinch");
        UnitOfMeasure uomTablespoon = getUnitOfMeasure("Tablespoon");

        perfectGuacamole.addIngredient(new Ingredient("Avocados", new BigDecimal(2), uomEach))
                .addIngredient(new Ingredient("Salt", new BigDecimal(1/4), uomPinch))
                .addIngredient(new Ingredient("Lime Juice", new BigDecimal(1),uomTablespoon));

        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes perfectGuacamoleNotes = new Notes();
        perfectGuacamoleNotes.setRecipeNotes("Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        perfectGuacamole.setNotes(perfectGuacamoleNotes);



        recipeService.save(perfectGuacamole);
    }
}
