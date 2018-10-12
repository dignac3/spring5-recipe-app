package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    final Long LONG_ID = new Long(1L);
    final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        CategoryCommand command = new CategoryCommand();

        command.setId(LONG_ID);
        command.setDescription(DESCRIPTION);

        Category category = converter.convert(command);

        assertEquals(LONG_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
        assertNull(category.getRecipes());
    }
}