package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final RecipeCommandToRecipe recipeConverter;
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(RecipeCommandToRecipe recipeConverter, UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.recipeConverter = recipeConverter;
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {

        Ingredient ingredient = new Ingredient();

        ingredient.setId(source.getId());
        ingredient.setRecipe(recipeConverter.convert(source.getRecipe()));
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        return ingredient;
    }
}
