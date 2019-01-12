package doit.sharpenyoursaw.springrecipeapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;
import doit.sharpenyoursaw.springrecipeapp.service.RecipeService;

public class IndexControllerTest {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
	public void getIndexPage() {
		// Given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipes.add(recipe);

		// When
		when(recipeService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// Then
		assertEquals("index", indexController.getIndexPage(model));
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getValue().size());
	}

	@Test
	public void testMVCMock() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

}
