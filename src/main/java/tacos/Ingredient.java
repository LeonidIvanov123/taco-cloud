package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Ingredient { 
	
	@Id
	private String id;
	private String name;
	private String type;
	
	private Ingredient() {} //Конструктор без аргументов нужен для jpa (значение по умолчанию для полей?!?!?)
	
	public Ingredient(String id, String name, String type) {
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

	public void setType(String type) {
		this.type = type;
	}

	
	public enum Type {
		 WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
		 }

	public String getType() {
		return type;
	}
}