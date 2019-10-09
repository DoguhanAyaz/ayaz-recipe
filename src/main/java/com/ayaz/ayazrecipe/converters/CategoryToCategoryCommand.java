package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.CategoryCommand;
import com.ayaz.ayazrecipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category category) {
        if (category != null) {
            final CategoryCommand categoryCommand = new CategoryCommand();
            categoryCommand.setId(category.getId());
            categoryCommand.setDescription(category.getDescription());
            return categoryCommand;
        }
        return null;
    }
}
