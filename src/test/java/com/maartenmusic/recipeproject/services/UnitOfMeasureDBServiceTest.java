package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.converters.IngredientToIngredientCommand;
import com.maartenmusic.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.maartenmusic.recipeproject.domain.UnitOfMeasure;
import com.maartenmusic.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UnitOfMeasureDBServiceTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    private final String DESCRIPTION = "Kilo";

    UnitOfMeasure unitOfMeasure;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureDBService(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);

        unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(DESCRIPTION);
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = Optional.of(unitOfMeasure);
        when(unitOfMeasureRepository.findByDescription(anyString())).thenReturn(unitOfMeasureOptional);

        UnitOfMeasure foundUnitOfMeasure = unitOfMeasureService.findByDescription(DESCRIPTION);

        assertNotNull(foundUnitOfMeasure);
        assertEquals(unitOfMeasure.getDescription(), foundUnitOfMeasure.getDescription());
        verify(unitOfMeasureRepository, times(1)).findByDescription(anyString());
        verify(unitOfMeasureRepository, never()).save(any());
    }
}