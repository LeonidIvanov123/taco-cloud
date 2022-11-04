package tacos.DBrep;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import tacos.Ingredient;

public interface IngredientRepository extends Repository<Ingredient, String>{
	List<Ingredient> findAll();
	Optional<Ingredient> findById(String id);
	Ingredient save(Ingredient ingredient);
}


//http://localhost:8080/h2-console