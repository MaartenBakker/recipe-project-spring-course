package com.maartenmusic.recipeproject.repositories;

import com.maartenmusic.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
