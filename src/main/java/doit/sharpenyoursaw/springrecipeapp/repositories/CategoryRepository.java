package doit.sharpenyoursaw.springrecipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import doit.sharpenyoursaw.springrecipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);

}
