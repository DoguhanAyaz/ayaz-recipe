package com.ayaz.ayazrecipe.repositories;

import com.ayaz.ayazrecipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository uomRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> uom = uomRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon",uom.get().getDescription());
    }
    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> uom = uomRepository.findByDescription("Cup");

        assertEquals("Cup",uom.get().getDescription());
    }
}