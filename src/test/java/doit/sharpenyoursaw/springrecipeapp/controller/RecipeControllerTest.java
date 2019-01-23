package doit.sharpenyoursaw.springrecipeapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import doit.sharpenyoursaw.springrecipeapp.commands.RecipeCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;
import doit.sharpenyoursaw.springrecipeapp.service.RecipeService;

public class RecipeControllerTest {

	RecipeController recipeController;

	MockMvc mockMvc;

	@Mock
	RecipeService recipeService;

	Long ID = 1L;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);

		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	public void getRecipes() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(ID);


		when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show/")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();

		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testPostNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.saveRecipeCommand(any())).thenReturn(command);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show"));
	}

	@Test
	public void testGetUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findRecipeCommandById(anyLong())).thenReturn(command);

		mockMvc.perform(get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testDeleteAction() throws Exception {
		mockMvc.perform(get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		verify(recipeService, times(1)).deleteById(anyLong());
	}

}
