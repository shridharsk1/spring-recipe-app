package doit.sharpenyoursaw.springrecipeapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;
import doit.sharpenyoursaw.springrecipeapp.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}

}
