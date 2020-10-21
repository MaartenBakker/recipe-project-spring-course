package com.maartenmusic.recipeproject.repositories;

import com.maartenmusic.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    // This method in the interface is enough for Spring to make the Query work.
    // Naming needs to be according to format 'findBy{field}'
    Optional<Category> findByDescription(String description);
}
