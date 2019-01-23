package doit.sharpenyoursaw.springrecipeapp.service;

import java.util.Set;

import doit.sharpenyoursaw.springrecipeapp.commands.RecipeCommand;
import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findRecipeById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

	RecipeCommand findRecipeCommandById(Long id);

	void deleteById(Long id);

}
