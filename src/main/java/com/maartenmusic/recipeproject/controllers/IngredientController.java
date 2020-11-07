package com.maartenmusic.recipeproject.controllers;

import com.maartenmusic.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String listIngredients(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/ingredient/list";
    }
}
