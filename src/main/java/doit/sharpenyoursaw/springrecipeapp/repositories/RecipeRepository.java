package doit.sharpenyoursaw.springrecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import doit.sharpenyoursaw.springrecipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
