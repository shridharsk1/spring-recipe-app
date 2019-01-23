package doit.sharpenyoursaw.springrecipeapp.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import doit.sharpenyoursaw.springrecipeapp.commands.RecipeCommand;
import doit.sharpenyoursaw.springrecipeapp.converters.RecipeCommandToRecipe;
import doit.sharpenyoursaw.springrecipeapp.converters.RecipeToRecipeCommand;
import org.springframework.stereotype.Service;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;
import doit.sharpenyoursaw.springrecipeapp.repositories.RecipeRepository;

import javax.transaction.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService {

	final RecipeRepository recipeRepository;

	final RecipeToRecipeCommand recipeToRecipeCommand;

	final RecipeCommandToRecipe recipeCommandToRecipe;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}

	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}

	public Recipe findRecipeById(Long id) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
		if (!optionalRecipe.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return optionalRecipe.get();
	}

	@Override
	@Transactional
	public RecipeCommand findRecipeCommandById(Long id) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
		if (!optionalRecipe.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return recipeToRecipeCommand.convert(optionalRecipe.get());
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}

}
