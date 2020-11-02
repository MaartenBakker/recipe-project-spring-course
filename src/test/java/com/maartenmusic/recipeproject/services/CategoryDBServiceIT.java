package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Category;
import com.maartenmusic.recipeproject.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDBServiceIT {

    private final String DESCRIPTION = "Description";

    @Autowired
    CategoryService service;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void saveTest() {
        //given
        Category category = new Category();
        category.setDescription(DESCRIPTION);

        //when
        service.save(category);
        Category foundCategory = service.findByDescription(DESCRIPTION);

        //then
        assertEquals(category.getDescription(), foundCategory.getDescription());


    }
}