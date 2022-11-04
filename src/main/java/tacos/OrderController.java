package tacos;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.DBrep.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	private OrderRepository orderRepository;
	
	public OrderController(OrderRepository orderRepository) {
		 this.orderRepository = orderRepository;
		 }

	
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors error, SessionStatus sessionStatus) {
		if(error.hasErrors()) {
			System.out.println("\n =====Ошибка при создании Заказа=====  Ошибки:\n" + error.toString());
			return "orderForm";
		}
		order.setPlacedAt(new Date());
		orderRepository.save(order);
	 sessionStatus.setComplete();
	 System.out.println("\n=====заказ отправлен====");
	 return "redirect:/";
	}
	
}
