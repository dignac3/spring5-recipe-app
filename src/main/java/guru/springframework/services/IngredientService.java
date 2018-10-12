package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long idRecipe, Long idIngredient);
}
