package com.maartenmusic.recipeproject.controllers;

import com.maartenmusic.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/show/{id}")
    public String showById(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }
}
