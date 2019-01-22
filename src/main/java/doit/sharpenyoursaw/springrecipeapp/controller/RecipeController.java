package doit.sharpenyoursaw.springrecipeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import doit.sharpenyoursaw.springrecipeapp.service.RecipeService;

@Controller
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));
		return "recipe/show";
	}

}
