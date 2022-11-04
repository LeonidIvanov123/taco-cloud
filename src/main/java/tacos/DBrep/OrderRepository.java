package tacos.DBrep;

import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
	 //TacoOrder save(TacoOrder order);
}