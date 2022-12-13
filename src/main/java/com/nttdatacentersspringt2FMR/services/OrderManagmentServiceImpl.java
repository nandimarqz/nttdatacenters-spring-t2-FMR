package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Customer;
import com.nttdatacentersspringt2FMR.persistence.Order;
import com.nttdatacentersspringt2FMR.persistence.repository.OrderRepositoryI;

/**
 * Implementacion del servicio de gestion de pedidos
 * 
 * @author nandi
 *
 */
@Service("General")
public class OrderManagmentServiceImpl implements OrderManagmentServiceI {

	/** Repositorio de pedidos */
	@Autowired
	OrderRepositoryI or;

	@Override
	public void add(Order o) {

		// Si el pedido es distinto a null y el id es null entra en la condicion
		if (o != null && o.getId() == null) {

			or.save(o);// Guarda en la BBDD

		}

	}

	@Override
	public void delete(Order o) {

		// Si el pedido es distinto a null y el id tambien entra en la condicion
		if (o != null && o.getId() != null) {

			or.delete(o);// Borra la BBDD

		}

	}

	@Override
	public void update(Order o) {

		// Si el pedido es distinto a null y el id tambien entra en la condicion
		if (o != null && o.getId() != null) {

			// Si existe el pedido entra en la condicion
			if (or.existsById(o.getId())) {

				or.save(o);// Actualiza en la BBDD

			} else {

				System.out.println("El pedido que se intenta actualizar no existe");

			}

		}

	}

	@Override
	public List<Order> findAll() {

		// Devuelve todos los pedidos
		return or.findAll();
	}

	@Override
	public Order findByReference(String reference) {

		Order o = null;

		// Si la referencia es distinta a null entra en la condicion
		if (reference != null) {

			o = or.findByReference(reference);// Guarda el resultado en la variable

		}

		return o;
	}

	@Override
	public long countOrders() {

		// Devuelve el numero de pedidos existentes
		return or.count();
	}

	@Override
	public List<Order> findByCustomer(Customer c) {

		List<Order> orders = null;

		// Si el cliente es distinto a null y su id tmb entra en la condicion
		if (c != null && c.getId() != null) {

			//Guarda los pedidos en la lista
			orders = or.findByCustomer(c);

		}

		return orders;
	}

}
