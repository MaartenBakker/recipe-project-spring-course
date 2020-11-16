package com.maartenmusic.recipeproject.controllers;

import com.maartenmusic.recipeproject.commands.RecipeCommand;
import com.maartenmusic.recipeproject.exceptions.NotFoundException;
import com.maartenmusic.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String showById(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        RecipeCommand recipeCommand = new RecipeCommand();
//        recipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        model.addAttribute("recipe", recipeCommand);

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteById(id);
        } catch (Exception e) {
            System.out.println("Cannot delete recipe with unknown ID");
        }

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound (Exception e) {
        log.error("Handling not found Exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException (Exception e) {
        log.error("Handling NumberFormatException");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}
