package guru.springframework.controllers;


import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "/recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{idRecipe}/ingredients/{id}/show")
    public String showRecipeIngredients(@PathVariable String id, @PathVariable String idRecipe, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(idRecipe), Long.valueOf(id)));

        return "/recipe/ingredient/show";
    }
}
