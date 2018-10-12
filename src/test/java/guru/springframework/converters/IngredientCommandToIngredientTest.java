package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final Long LONG_ID = new Long(1L);
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = new BigDecimal(35);
    private final Long UOM_ID = new Long(2L);

    IngredientCommandToIngredient ingredientConverter;

    @Before
    public void setUp() throws Exception {

        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testNotNullParameter() throws Exception {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));

    }

    @Test
    public void convert() throws Exception {

        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();

        uomCommand.setId(UOM_ID);

        ingredientCommand.setId(LONG_ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setUom(uomCommand);

        //when
        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);


        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());

        assertEquals(LONG_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID,ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUom() throws Exception {

        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();

        ingredientCommand.setId(LONG_ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);


        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());

        assertEquals(LONG_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}