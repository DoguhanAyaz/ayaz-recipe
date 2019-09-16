package com.ayaz.ayazrecipe.Services.Impl;

import com.ayaz.ayazrecipe.Services.RecipeService;
import com.ayaz.ayazrecipe.domain.Recipe;
import com.ayaz.ayazrecipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> retrieveRecipes() {

        log.debug("I'm in the service");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.get() ;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
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
