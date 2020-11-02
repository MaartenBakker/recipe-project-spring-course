package com.maartenmusic.recipeproject.services;

import com.maartenmusic.recipeproject.domain.UnitOfMeasure;
import com.maartenmusic.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitOfMeasureDBServiceIT {

    private final String DESCRIPTION = "Kilo";

    @Autowired
    UnitOfMeasureService service;

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void saveTest() {
        //given
        UnitOfMeasure uom  = new UnitOfMeasure();
        uom.setDescription(DESCRIPTION);

        //when
        service.save(uom);
        UnitOfMeasure foundUom = service.findByDescription(DESCRIPTION);

        //then
        assertEquals(uom.getDescription(), foundUom.getDescription());
    }
}
