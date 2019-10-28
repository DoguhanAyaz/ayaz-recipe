package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.RecipeCommand;
import com.ayaz.ayazrecipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, IngredientCommandToIngredient ingredientConverter, CategoryCommandToCategory categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }


    @Override
    public Recipe convert(RecipeCommand source) {

        if (source != null) {
            Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setCookTime(source.getCookTime());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setDescription(source.getDescription());
            recipe.setDirections(source.getDirections());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setUrl(source.getUrl());
            recipe.setSource(source.getSource());
            recipe.setImage(source.getImage());
            recipe.setNotes(notesConverter.convert(source.getNotes()));
            //sor
            if (source.getCategories() != null && source.getCategories().size() > 0) {
                source.getCategories()
                        .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                source.getIngredients()
                        .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
            }

            return recipe;
        }
        return null;
    }
}
