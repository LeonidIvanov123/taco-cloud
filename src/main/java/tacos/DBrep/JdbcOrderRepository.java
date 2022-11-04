package tacos.DBrep;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.Ingredient;
import tacos.Taco;
import tacos.TacoOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private JdbcOperations jdbcOperations;
	
	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	@Transactional
	 public TacoOrder save(TacoOrder order) {
	 PreparedStatementCreatorFactory pscf =
	 new PreparedStatementCreatorFactory(
	 "insert into Taco_Order "
	 + "(delivery_name, delivery_street, delivery_city, "
	 + "delivery_state, delivery_zip, cc_number, "
	 + "cc_expiration, cc_cvv, placed_at) "
	 + "values (?,?,?,?,?,?,?,?,?)",
	 Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
	 Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
	 Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
	 );
	 pscf.setReturnGeneratedKeys(true);
	 order.setPlacedAt(new Date());
	 PreparedStatementCreator psc =
	 pscf.newPreparedStatementCreator(
	 Arrays.asList(
	 order.getDeliveryName(),
	 order.getDeliveryStreet(),
	 order.getDeliveryCity(),
	 order.getDeliveryState(),
	 order.getDeliveryZip(),
	 order.getCcNumber(),
	 order.getCcExpiration(),
	 order.getCcCVV(),
	 order.getPlacedAt()));
	 GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	 jdbcOperations.update(psc, keyHolder);
	 long orderId = keyHolder.getKey().longValue();
	 order.setId(orderId);
	 List<Taco> tacos = order.getTacos();
	 int i=0;
	 for (Taco taco : tacos) {
	 saveTaco(orderId, i++, taco);
	 }
	 return order;
	 }
	
	private long saveTaco(Long orderId, int orderKey, Taco taco) {
		 //taco.setCreatedAt(new Date());//уже при создании заполняется
		 PreparedStatementCreatorFactory pscf =
		 new PreparedStatementCreatorFactory(
		 "insert into Taco "
		 + "(name, created_at, taco_order, taco_order_key) "
		 + "values (?, ?, ?, ?)",
		 Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT //был Type.LONG
		 );
		 pscf.setReturnGeneratedKeys(true);
		 PreparedStatementCreator psc =
		 pscf.newPreparedStatementCreator(
		 Arrays.asList(
		 taco.getName(),
		 taco.getCreateAt(),
		 orderId,
		 orderKey));
		 GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcOperations.update(psc, keyHolder);
		 long tacoId = keyHolder.getKey().longValue();
		 taco.setId(tacoId);
		 saveIngredientRefs(tacoId, taco.getIngredients());
		 return tacoId;
		}
	
	private void saveIngredientRefs(long tacoId, List<Ingredient> ingredientRefs) {
			 int key = 0;
			 for (Ingredient ingredientRef : ingredientRefs) {
			 jdbcOperations.update(
			 "insert into Ingredient_Ref (ingredient, taco, taco_key) "
			 + "values (?, ?, ?)",
			 ingredientRef.getId(), tacoId, key++);
			 }
			}

	@Override
	public <S extends TacoOrder> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TacoOrder> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<TacoOrder> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TacoOrder> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TacoOrder entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends TacoOrder> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	
	
}


