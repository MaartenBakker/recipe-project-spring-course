package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.UnitOfMeasure;
import com.maartenmusic.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceDB implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceDB(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public UnitOfMeasure findByDescription(String description) {
        return unitOfMeasureRepository.findByDescription(description).get();
    }
}
