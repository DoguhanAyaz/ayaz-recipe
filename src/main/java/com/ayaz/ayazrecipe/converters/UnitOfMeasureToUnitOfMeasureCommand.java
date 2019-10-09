package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.UnitOfMeasureCommand;
import com.ayaz.ayazrecipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure , UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null){
            return null;}
        final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(unitOfMeasure.getId());
        uom.setDescription(unitOfMeasure.getDescription());
        return uom;
    }
}
