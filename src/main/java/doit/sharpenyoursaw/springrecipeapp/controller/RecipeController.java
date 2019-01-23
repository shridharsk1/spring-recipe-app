package doit.sharpenyoursaw.springrecipeapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import doit.sharpenyoursaw.springrecipeapp.commands.RecipeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doit.sharpenyoursaw.springrecipeapp.service.RecipeService;

@Controller
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping
	@RequestMapping("/recipe/{id}/show/")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));
		return "recipe/show";
	}

	@GetMapping
	@RequestMapping("recipe/new")
	public String newRecipe(Model model){
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@GetMapping
	@RequestMapping("recipe/{id}/update")
	public  String updateRecipe(@PathVariable String id, Model model){
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

		return "redirect:/recipe/"+savedRecipeCommand.getId()+"/show";
	}

	@GetMapping
	@RequestMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id, Model model){
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

}
