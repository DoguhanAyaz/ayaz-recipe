package com.ayaz.ayazrecipe.Services;

import com.ayaz.ayazrecipe.commands.RecipeCommand;
import com.ayaz.ayazrecipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> retrieveRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteRecipeById(Long id);
    Recipe deleteRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
}
