package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setPrepTime(source.getPrepTime());

        if (source.getNotes() != null){
            recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredientCommand -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(categoryCommand -> recipeCommand.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        return recipeCommand;
    }
}
