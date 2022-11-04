package tacos.DBrep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

	private JdbcTemplate jdbcTemplate;
	
	/*Когда фреймворк Spring будет создавать bean-компонент JdbcIngredientRepository, он внедрит в  него экземпляр JdbcTemplate. Это 
	объясняется просто: когда имеется только один конструктор, Spring 
	неявно применяет автоматическое связывание зависимостей через 
	параметры этого конструктора. Если имеется более одного конструктора или если нужно, чтобы автоматическое связывание определялось явно, можно к конструктору добавить аннотацию @Autowired
	*/
	@Autowired // spring будет использовать именно этот конструктор (актуально если есть несколько конструкторов)
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		 this.jdbcTemplate = jdbcTemplate;
	 }
	
	@Override
	public List<Ingredient> findAll() {
	 return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}
	@Override
	public Optional<Ingredient> findById(String id) {
	 List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
	 return results.size() == 0 ?
	 Optional.empty() :
	 Optional.of(results.get(0));
	}

	private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
			 throws SQLException {
			 return new Ingredient(
			 row.getString("id"),
			 row.getString("name"),
			 Ingredient.Type.valueOf(row.getString("type")));
			}

	@Override
	public Ingredient save(Ingredient ingredient) {
	 jdbcTemplate.update(
	 "insert into Ingredient (id, name, type) values (?, ?, ?)",
	 ingredient.getId(), //возможно нужен getId и тогда record не подойдет
	 ingredient.getName(),
	 ingredient.getType().toString());
	 return ingredient;
	}
	

}
