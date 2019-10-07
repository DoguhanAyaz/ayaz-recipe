package com.ayaz.ayazrecipe.Services.Impl;

import com.ayaz.ayazrecipe.domain.Recipe;
import com.ayaz.ayazrecipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    public void retrieveRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeService.retrieveRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.retrieveRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() throws  Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned  = recipeService.getRecipe(1L);
        assertNotNull("Null recipe Returned",recipeReturned);
        verify(recipeRepository, never()).findAll();
    }
}