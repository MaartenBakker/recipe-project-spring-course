package com.maartenmusic.recipeproject.bootstrap;

import com.maartenmusic.recipeproject.domain.*;
import com.maartenmusic.recipeproject.services.CategoryService;
import com.maartenmusic.recipeproject.services.RecipeService;
import com.maartenmusic.recipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
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
    @Transactional
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
            System.out.println("Creating new category");

            category = new Category(description);
            categoryService.save(category);
        }

        category.getRecipes().add(recipe);

        return category;
    }

    private void getRecipes() {
        log.debug("Loading bootstrap recipes");
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



        Recipe chickenFriedRice = new Recipe();
        Category chinese = getCategory("Chinese", chickenFriedRice);
        chickenFriedRice.getCategories().add(chinese);

        chickenFriedRice.setCookTime(15);
        chickenFriedRice.setPrepTime(10);
        chickenFriedRice.setServings(7);
        chickenFriedRice.setSource("Simply Recipes");
        chickenFriedRice.setUrl("https://www.simplyrecipes.com/recipes/chicken_fried_rice/");
        chickenFriedRice.setDescription("Chicken Fried Rice");
        chickenFriedRice.setDifficulty(Difficulty.EASY);

        UnitOfMeasure uomPound = getUnitOfMeasure("Pound");
        UnitOfMeasure uomTeaspoon = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure uomCup = getUnitOfMeasure("Cup");

        chickenFriedRice.addIngredient(new Ingredient("Chicken Thighs", new BigDecimal(3/4), uomPound))
                .addIngredient(new Ingredient("Salt", new BigDecimal(1), uomTeaspoon))
                .addIngredient(new Ingredient("Yellow Onions", new BigDecimal(2/3), uomCup));

        chickenFriedRice.setDirections("1 Prepare the chicken: Chop the chicken into small 1/4-inch to 1/2-inch cubes. Sprinkle 1/2 teaspoon of salt over the chicken and mix to combine. Set the chicken aside for about 10 minutes (I usually use this time to chop all the vegetables).\n" +
                "\n" +
                "2 Scramble the egg: Heat a wok or large sauté pan over medium-high heat. Swirl in a tablespoon of oil and add the whisked eggs. Use a spatula to quickly scramble the eggs, breaking the curds into smaller pieces as they come together. Transfer the eggs to a plate.\n" +
                "\n" +
                "3 Cook the chicken: Add another tablespoon of oil in the wok or pan. Add the chicken and cook for 4 to 5 minutes, stirring occasionally. Turn off the heat and transfer the cooked chicken to a plate.\n" +
                "\n" +
                "Using your spatula, scrape off any chicken bits that are still stuck to the wok so they don't burn during the next step. You can also use paper towels to wipe down your wok or pan.\n" +
                "\n" +
                "4 Cook the vegetables: Swirl 1 tablespoon of oil into the wok over medium-high heat. Add the diced onions and cook them for 1 minute, until they start to soften. Mix in the minced garlic and ginger and cook until fragrant, about 30 seconds. Add the diced carrots and cook for 2 minutes, stirring frequently. Add 1/2 teaspoon salt and the peas, and stir to incorporate.\n" +
                "\n" +
                "5 Cook the rice: Add the rice to the wok or pan on top of the vegetables and stir to combine. Using the back of your spatula, smash any large chunks of rice to break them apart. Add the white and green parts of the sliced scallions (save the dark green parts) and five-spice powder. Stir to incorporate. If the rice starts to stick to the pan, stir in a little more oil.\n" +
                "\n" +
                "Drizzle the soy sauce and sesame oil over the rice and stir to incorporate. Stir in the cooked chicken, scrambled eggs, and the dark parts of the scallions. Stir briefly to bring it together, and cook for another 1 to 2 minutes. Taste, and add more soy sauce if necessary.\n" +
                "\n" +
                "6 Serve immediately while hot.");

        Notes chickenFriedRiceNotes = new Notes();
        chickenFriedRiceNotes.setRecipeNotes("Fried rice is best made with leftover rice that's at least a day old. Otherwise it becomes gummy in the skillet.\n" +
                "\n" +
                "If you don’t have any leftover rice from the night before, cook a batch of rice and spread it on a large baking sheet or several large plates. Let the rice dry out for about 1 to 2 hours before using it for fried rice.\n" +
                "\n" +
                "Rice sticks to the pan very easily, so make sure to use a wok or pan that doesn’t have a sticky surface. I usually cook stir-fries in my seasoned carbon steel wok, but cast iron or nonstick pans work well, too. You might need to add a little more oil if things aren’t releasing easily.");
        chickenFriedRice.setNotes(chickenFriedRiceNotes);

        recipeService.save(chickenFriedRice);
    }
}
