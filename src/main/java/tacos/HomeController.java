package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	 public String home() {
	 return "home";  		//имя представления из resources/templates
	 }
	
	@PostMapping
	public String createNewOrder() {
		return "redirect:/design";
		
	}
}

//Добавить пользователя на главной странице и передавать его в составление заказа