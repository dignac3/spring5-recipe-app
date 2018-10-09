package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    private final RecipeCommandToRecipe recipeConverter;

    public CategoryCommandToCategory(RecipeCommandToRecipe recipeCommand) {
        this.recipeConverter = recipeCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category c = new Category();

        c.setId(source.getId());
        c.setDescription(source.getDescription());

        //Set<RecipeCommand>
        if ( source.getRecipes() != null && source.getRecipes().size() > 0)
            source.getRecipes().forEach(recipe -> c.getRecipes().add(recipeConverter.convert(recipe)));

        return c;
    }
}
