package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.UnitOfMeasureCommand;
import com.maartenmusic.recipeproject.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    UnitOfMeasure findByDescription(String description);

    void save(UnitOfMeasure unitOfMeasure);

    Set<UnitOfMeasureCommand> findAll();

}
