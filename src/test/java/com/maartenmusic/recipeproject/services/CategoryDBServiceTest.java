package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.Category;
import com.maartenmusic.recipeproject.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CategoryDBServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    private final String DESCRIPTION = "Thai";

    Category category;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryDBService(categoryRepository);

        category = new Category();
        category.setDescription(DESCRIPTION);
    }

    @Test
    public void findByDescription() {
        Optional<Category> categoryOptional = Optional.of(category);
        when(categoryRepository.findByDescription(anyString())).thenReturn(categoryOptional);

        Category foundCategory = categoryService.findByDescription(DESCRIPTION);

        assertNotNull(foundCategory);
        assertEquals(DESCRIPTION, foundCategory.getDescription());
        verify(categoryRepository, times(1)).findByDescription(anyString());
        verify(categoryRepository, never()).save(any());
    }
}