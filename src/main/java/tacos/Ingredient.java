package tacos;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Ingredient { 
	
	@Id
	String id;
	String name;
	Type type;
	
	public Ingredient(String id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	public enum Type {
		 WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
		 }

	public Type getType() {
		return type;
	}
}