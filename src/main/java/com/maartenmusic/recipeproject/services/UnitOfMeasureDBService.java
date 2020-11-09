package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.commands.UnitOfMeasureCommand;
import com.maartenmusic.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.maartenmusic.recipeproject.domain.UnitOfMeasure;
import com.maartenmusic.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureDBService implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureDBService(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public UnitOfMeasure findByDescription(String description) {
        return unitOfMeasureRepository.findByDescription(description).get();
    }

    @Override
    public void save(UnitOfMeasure unitOfMeasure) {
        unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        Iterable<UnitOfMeasure> uomIterable = unitOfMeasureRepository.findAll();

        return StreamSupport.stream(uomIterable.spliterator(), false)
                .map(converter::convert).collect(Collectors.toSet());
    }
}
