package tacos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Ingredient.Type;
import tacos.DBrep.IngredientRepository;
//import tacos.DBrep.JdbcIngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	
	private final IngredientRepository ingregientRepository;
	@Autowired
	public DesignTacoController(IngredientRepository ingregientRepository) {
		super();
		this.ingregientRepository = ingregientRepository;
	}
	
	@ModelAttribute
	 public void addIngredientsToModel(Model model) {
		
	
	Iterable<Ingredient> ingredients= ingregientRepository.findAll();
	
	/*		
	 List<Ingredient> ingredients = Arrays.asList(
	 new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	 new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
	 new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	 new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	 new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
	 new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	 new Ingredient("CHED", "Cheddar", Type.CHEESE),
	 new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	 new Ingredient("SLSA", "Salsa", Type.SAUCE),
	 new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
	 );
	*/
	 Type[] types = Ingredient.Type.values();
	 for (Type type : types) {
		 model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
	 }
	 }
	 
	 @ModelAttribute(name = "tacoOrder")
	 public TacoOrder order() {
	 return new TacoOrder();
	 }
	 @ModelAttribute(name = "taco")
	 public Taco taco() {
	 return new Taco();
	 }
	 @GetMapping
	 public String showDesignForm() {
	 return "design";
	 }
	 private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		 
		 var lingr = new ArrayList<Ingredient>();
		 ingredients.forEach(lingr::add);
		 
			 return lingr
			 .stream()
			 .filter(x -> x.getType().equals(type.name()))
			 .collect(Collectors.toList());
	}
	 
	 @PostMapping
	 public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		 if(errors.hasErrors()) {
			 System.out.println("\n =====???????????? ?????? ???????????????? ????????===== ????????????:\n" + errors.toString());
			 return "design";
		 }
	  tacoOrder.addTaco(taco);
	  //System.out.println("Processing taco: {}", taco);
	  return "redirect:/orders/current";
	 }
}

