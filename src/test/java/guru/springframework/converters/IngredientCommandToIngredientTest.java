package guru.springframework.converters;

import org.junit.Before;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final Long LONG_ID = new Long(1L);
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = new BigDecimal(35);

    IngredientCommandToIngredient ingredientConverter;

    /*@Before
    public void setUp() throws Exception {

        ingredientConverter = new IngredientCommandToIngredient(
                new RecipeCommandToRecipe(),
                new UnitOfMeasureCommandToUnitOfMeasure());

    }*/
}