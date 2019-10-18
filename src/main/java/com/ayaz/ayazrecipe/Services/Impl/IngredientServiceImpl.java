package com.ayaz.ayazrecipe.Services.Impl;

import com.ayaz.ayazrecipe.Services.IngredientService;
import com.ayaz.ayazrecipe.commands.IngredientCommand;
import com.ayaz.ayazrecipe.converters.IngredientCommandToIngredient;
import com.ayaz.ayazrecipe.converters.IngredientToIngredientCommand;
import com.ayaz.ayazrecipe.domain.Ingredient;
import com.ayaz.ayazrecipe.domain.Recipe;
import com.ayaz.ayazrecipe.repositories.RecipeRepository;
import com.ayaz.ayazrecipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToCommand;
    private final RecipeRepository recipeRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientCommand1, RecipeRepository recipeRepository, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToCommand = ingredientCommand1;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId, Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(RecipeId);
        if (!recipe.isPresent()) {

        }
        Recipe recipe1 = recipe.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe1.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToCommand.convert(ingredient)).findFirst();
        if (!ingredientCommandOptional.isPresent()) {
        }
        return ingredientCommandOptional.get();
    }

    @Override
    public void deleteIngredientById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            Recipe recipe1 = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe1.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe1.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe1);
            }
        }
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipe = recipeRepository.findById(ingredientCommand.getRecipeId());
        if (!recipe.isPresent()) {
            return new IngredientCommand();
        } else {
            Recipe recipe1 = recipe.get();

            Optional<Ingredient> ingredientOptional = recipe1.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe1);
                recipe1.addIngredient(ingredient);
            }
            Recipe savedRecipe = recipeRepository.save(recipe1);
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId())).findFirst();
            if (!savedIngredientOptional.isPresent()) {
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientCommand.getUom().getId()))
                        .findFirst();
            }
            return ingredientToCommand.convert(savedIngredientOptional.get());
        }
    }
}
