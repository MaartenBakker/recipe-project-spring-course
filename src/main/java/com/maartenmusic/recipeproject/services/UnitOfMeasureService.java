package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

    UnitOfMeasure findByDescription(String description);

    void save(UnitOfMeasure unitOfMeasure);

}
