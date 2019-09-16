package com.ayaz.ayazrecipe.Services;

import com.ayaz.ayazrecipe.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> retrieveRecipes();
    Recipe getRecipe(Long id);
    void saveRecipe(Recipe recipe);
    void deleteRecipeById(Long id);
    Recipe deleteRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
}
