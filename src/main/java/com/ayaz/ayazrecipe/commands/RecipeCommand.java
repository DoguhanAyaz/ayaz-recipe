package com.ayaz.ayazrecipe.commands;

import com.ayaz.ayazrecipe.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String recipe;
    private String url;
    private String directions;
    private Byte[] image;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand recipeNotes;
    private Set<CategoryCommand> categories = new HashSet<>();

}