package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.IngredientCommand;
import com.ayaz.ayazrecipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source != null){
            final Ingredient ingredient = new Ingredient();
            ingredient.setAmount(source.getAmount());
            ingredient.setDescription(source.getDescription());
            ingredient.setId(source.getId());
            ingredient.setUom(uomConverter.convert(source.getUom()));
            return ingredient;
        }
        return null;
    }
}
