package doit.sharpenyoursaw.springrecipeapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;
import doit.sharpenyoursaw.springrecipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeServiceImpl;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void testFindAll() {
		Set<Recipe> recipies = new HashSet<>();

		Recipe recipe = new Recipe();
		Set<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);

		when(recipeRepository.findAll()).thenReturn(recipeData);
		recipies = recipeServiceImpl.getRecipes();
		assertEquals(1, recipies.size());
		verify(recipeRepository, times(1)).findAll();
	}

}
