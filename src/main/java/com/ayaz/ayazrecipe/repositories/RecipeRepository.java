package com.ayaz.ayazrecipe.repositories;

import com.ayaz.ayazrecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe , Long> {
}
