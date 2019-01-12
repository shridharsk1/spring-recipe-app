package doit.sharpenyoursaw.springrecipeapp.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import doit.sharpenyoursaw.springrecipeapp.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository repository;

	@Before
	public void setup() {

	}

	@Test
	public void findByDescription() {
		Optional<UnitOfMeasure> unitOfMeasure = repository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", unitOfMeasure.get().getDescription());
	}

}
