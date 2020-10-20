package com.maartenmusic.recipeproject.repositories;

import com.maartenmusic.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
