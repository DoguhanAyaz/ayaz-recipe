package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.CategoryCommand;
import com.ayaz.ayazrecipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand source) {
        if (source != null) {
            final Category category = new Category();
            category.setId(source.getId());
            category.setDescription(source.getDescription());
            return category;
        }
        return null;
    }
}
