package com.ayaz.ayazrecipe.Services.Impl;

import com.ayaz.ayazrecipe.Services.RecipeService;
import com.ayaz.ayazrecipe.commands.RecipeCommand;
import com.ayaz.ayazrecipe.converters.RecipeCommandToRecipe;
import com.ayaz.ayazrecipe.converters.RecipeToRecipeCommand;
import com.ayaz.ayazrecipe.domain.Recipe;
import com.ayaz.ayazrecipe.exceptions.NotFoundException;
import com.ayaz.ayazrecipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> retrieveRecipes() {

        log.debug("I'm in the service");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe Not Found");
        }
        return recipeOptional.get() ;
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe : " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }


    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe deleteRecipe(Recipe recipe) {
        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }
}
