package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    final Long LONG_ID = new Long(1L);
    final String RECIPE_NOTE = "recipe_note";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter= new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));

    }

    @Test
    public void convert() {


        NotesCommand command = new NotesCommand();

        command.setId(LONG_ID);
        command.setRecipeNotes(RECIPE_NOTE);


        Notes notes = converter.convert(command);


        assertNotNull(notes);
        assertNull(notes.getRecipe());
        assertEquals(LONG_ID, notes.getId());
        assertEquals(RECIPE_NOTE,notes.getRecipeNotes());
    }
}