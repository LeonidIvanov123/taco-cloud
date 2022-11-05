package tacos.DBrep;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
	 //TacoOrder save(TacoOrder order);
	List<TacoOrder> findByDeliveryZip(String deliveryZip);//на основе имени метода Spring Date позволяет создавать нестандартные запросы к репозиторию
}