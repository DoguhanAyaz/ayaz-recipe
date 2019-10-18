package com.ayaz.ayazrecipe.controllers;

import com.ayaz.ayazrecipe.Services.RecipeService;
import com.ayaz.ayazrecipe.commands.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedComnand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedComnand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id, Model model) {
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

}
