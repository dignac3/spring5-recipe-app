package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNote implements Converter<NotesCommand, Notes> {

    private final RecipeCommandToRecipe recipeConverter;

    public NotesCommandToNote(RecipeCommandToRecipe recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Notes convert(NotesCommand source) {

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        notes.setRecipe(recipeConverter.convert(source.getRecipe()));

        return notes;
    }
}
