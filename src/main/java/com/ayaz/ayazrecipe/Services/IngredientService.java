package com.ayaz.ayazrecipe.Services;

import com.ayaz.ayazrecipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId , Long id);
    void deleteIngredientById(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
