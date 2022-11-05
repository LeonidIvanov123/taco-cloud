package tacos.DBrep;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	//List<Ingredient> findAll();
	//Optional<Ingredient> findById(String id);
	//Ingredient save(Ingredient ingredient);
}


//http://localhost:8080/h2-console