package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setCookTime(source.getCookTime());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setPrepTime(source.getPrepTime());

        if (source.getNotes() != null){
            recipe.setNotes(notesConverter.convert(source.getNotes()));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        return recipe;
    }
}
