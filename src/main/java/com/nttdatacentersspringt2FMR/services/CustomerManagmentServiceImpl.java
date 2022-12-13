package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Customer;
import com.nttdatacentersspringt2FMR.persistence.repository.CustomerRepositoryI;

/**
 * Implementacion del servicio de gestion de clientes
 * 
 * @author nandi
 *
 */
@Service
public class CustomerManagmentServiceImpl implements CustomerManagmentServiceI {

	/** Repositoio de clientes */
	@Autowired
	CustomerRepositoryI cr;

	/** Logger para la clase */
	final Logger CMSLOG = LoggerFactory.getLogger(CustomerManagmentServiceImpl.class);
	
	
	
	@Override
	public void add(Customer c) {

		// Si el clientes es distinto a null y su id es nulo entra en la condicion
		if (c != null && c.getId() == null) {
			
			CMSLOG.debug("Añadiendo cliente");
			cr.save(c);// guarda en la BBDD

		}

	}

	@Override
	public void delete(Customer c) {

		// Si el cliente es distinto a null y su id tambien entra en la condicion
		if (c != null && c.getId() != null) {

			CMSLOG.debug("Borrando cliente");
			cr.delete(c);// Borra en la BBDD

		}

	}

	@Override
	public void update(Customer c) {

		// Si el cliente es distinto a null y su id tambien entra en la condicion
		if (c != null && c.getId() != null) {

			// Si existe el cliente entra en la condicion
			if (cr.existsById(c.getId())) {

				CMSLOG.debug("Actualizando cliente");
				cr.save(c);// Actualiza el cliente

			} else {

				System.out.println("El cliente que intenta actualizar no existe");

			}

		}

	}

	@Override
	public List<Customer> findAll() {
		
		CMSLOG.debug("Obteniendo todos los clientes");
		//Devuelve una lista con todos los clientes existentes
		return cr.findAll();
	}

	@Override
	public Customer findByDni(String dni) {

		Customer c = null;

		//Si el dni pasado por parametro es distinto a null entra en la condicion
		if (dni != null) {

			CMSLOG.debug("Obteniendo cliente por el dni");
			//Guarda el cliente en una variable
			c = cr.findByDni(dni);

		}

		return c;
	}

	@Override
	public Boolean existByDni(String dni) {

		Boolean exist = false;

		//Si el dni pasado por parametro es distinto a null entra en la condicion
		if (dni != null) {

			CMSLOG.debug("Comprobando si existe cliente");
			//Guarda el cliente en una variable
			exist = cr.existsByDni(dni);

		}

		return exist;
	}

}
