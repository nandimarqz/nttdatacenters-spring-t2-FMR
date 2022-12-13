package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import com.nttdatacentersspringt2FMR.persistence.Customer;
import com.nttdatacentersspringt2FMR.persistence.Order;

/**
 * Interfaz del servicio de gestion de pedidos
 * 
 * @author nandi
 *
 */
public interface OrderManagmentServiceI {

	/**
	 * Añade el pedido pasado por parametro en la BBDD
	 * 
	 * @param o
	 */
	public void add(Order o);
	
	/**
	 * Borra el pedido pasado por parametro en la BBDD
	 * 
	 * @param o
	 */
	public void delete(Order o);
	
	/**
	 * Actualiza el pedido pasado por parametro en la BBDD
	 * 
	 * @param o
	 */
	public void update(Order o);
	
	/**
	 * Devuelve una lista con todos los pedidos de la BBDD
	 * 
	 * @return lista de pedidos
	 */
	public List<Order> findAll();
	
	/**
	 * Devuelve un pedido con referencia pasada por parametro
	 * 
	 * @param reference
	 * @return Order
	 */
	public Order findByReference(String reference);
	
	/**
	 * Devuelve la cantidad de pedidos existentes
	 * 
	 * @return long
	 */
	public long countOrders();
	
	/**
	 * Devuelve una lista de pedidos que pertenecen al cliente pasado por parametro
	 * 
	 * @param c
	 * @return lista de pedidos
	 */
	public List<Order> findByCustomer(Customer c);
	
}
