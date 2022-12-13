package com.nttdatacentersspringt2FMR.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdatacentersspringt2FMR.persistence.Customer;
import com.nttdatacentersspringt2FMR.persistence.Order;

/**
 * Repositorio de pedidos
 * 
 * @author nandi
 *
 */
@Repository
public interface OrderRepositoryI extends JpaRepository<Order, Long> {

	/**
	 * Devuelve un pedido con la referencia pasada por parametro
	 * 
	 * @param reference
	 * @return Order
	 */
	public Order findByReference(String reference);
	
	/**
	 * Devuelve una lista con los pedidos del cliente pasado por parametro
	 * 
	 * @param customer
	 * @return lista de pedidos
	 */
	public List<Order> findByCustomer(Customer customer);
}
