package com.ayaz.ayazrecipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;

        category.setId(idValue);
        assertEquals(idValue, category.getId());

    }

    @Test
    public void getDescription() {
        String description = "i love ece";

        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        Set<Recipe> recie1 = new HashSet<>();
        recie1.add(recipe);
        category.setRecipe(recie1);
        assertEquals(recie1.size() , 1);
    }
}