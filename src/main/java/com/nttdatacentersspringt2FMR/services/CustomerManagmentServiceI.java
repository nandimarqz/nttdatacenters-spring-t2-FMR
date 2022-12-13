package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import com.nttdatacentersspringt2FMR.persistence.Customer;

/**
 * Interfazdel servicio de gestion de clientes
 * 
 * @author nandi
 *
 */
public interface CustomerManagmentServiceI {
	
	/**
	 * Añade el cliente pasado por parametro en la BBDD
	 * 
	 * @param c
	 */
	public void add(Customer c);
	
	/**
	 * Borra el cliente pasado por parametro en la BBDD
	 * 
	 * @param c
	 */
	public void delete(Customer c);
	
	/**
	 * Actualiza el cliente pasado por parametro
	 * 
	 * @param c
	 */
	public void update(Customer c);
	
	/**
	 * Devuelve una lista con todos los clientes existentes
	 * 
	 * @return lista de clientes
	 */
	public List<Customer> findAll();
	
	/**
	 * Devuelve el cliente con el dni pasado por parametro
	 * 
	 * @param dni
	 * @return Customer
	 */
	public Customer findByDni(String dni);
	
	/**
	 * Devuelve un booleano indicando si existe o no el cliente
	 * 
	 * @param dni
	 * @return Boolean
	 */
	public Boolean existByDni(String dni);

}
