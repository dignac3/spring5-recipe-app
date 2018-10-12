package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;

import java.util.Optional;
import java.util.stream.Stream;

public class IngredientServiceImpl implements IngredientService {

    IngredientRepository ingredientRepository;
    RecipeRepository recipeRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (!recipe.isPresent()) {
            System.out.println("Recipe not found");
            return null;
        }

        Optional<Ingredient> ingredient = recipe.get().getIngredients().stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredientId))
                .findFirst();

        if (!ingredient.isPresent()) {
            System.out.println("Ingredient not found");
            return null;
        }

        return ingredientToIngredientCommand.convert(ingredient.get());
    }
}
