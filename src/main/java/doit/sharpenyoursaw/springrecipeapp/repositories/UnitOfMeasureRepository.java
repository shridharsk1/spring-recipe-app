package doit.sharpenyoursaw.springrecipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import doit.sharpenyoursaw.springrecipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);

}
