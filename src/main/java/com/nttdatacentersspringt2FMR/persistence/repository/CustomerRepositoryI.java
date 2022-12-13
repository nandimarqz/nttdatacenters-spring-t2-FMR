package com.nttdatacentersspringt2FMR.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdatacentersspringt2FMR.persistence.Customer;

/**
 * Repositorio de clientes 
 * 
 * @author nandi
 *
 */
@Repository
public interface CustomerRepositoryI extends JpaRepository<Customer, Long> {

	/**
	 * Devuelve un cliente con el dni pasado por parametro
	 * 
	 * @param dni
	 * @return Customer
	 */
	public Customer findByDni(String dni);
	
	/**
	 * Devuelve un booleano si existe un cliente con el dni pasado por parametro
	 * 
	 * @param dni
	 * @return Boolean
	 */
	public Boolean existsByDni(String dni);
	
}
