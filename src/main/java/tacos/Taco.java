package tacos;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {
	
	private Long id;
	private Date createAt = new Date();
	@NotNull
	@Size(min=5, message="Название не менее 5 символов")
	private String name;
	@NotNull
	@Size(min=1, message="Должен присутствовать хотя бы 1 ингридиент")
	private List<Ingredient> ingredients;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateAt(){
		return createAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
