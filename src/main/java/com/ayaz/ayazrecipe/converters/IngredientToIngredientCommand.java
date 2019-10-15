package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.IngredientCommand;
import com.ayaz.ayazrecipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient , IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient != null){
            final IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
            return ingredientCommand;
        }
        return null;
    }
}
