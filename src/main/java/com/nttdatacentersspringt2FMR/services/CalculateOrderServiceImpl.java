package com.nttdatacentersspringt2FMR.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Order;

/**
 * Implementacion general del servicio de calculo de pedido
 * @author nandi
 *
 */
@Service
public class CalculateOrderServiceImpl extends OrderManagmentServiceImpl implements CalculateOrderServiceI{

	/** Logger para la clase */
	final Logger COSLOG = LoggerFactory.getLogger(CalculateOrderServiceImpl.class);

	@Override
	public void cancelOrder(Order o) {
		COSLOG.debug("Inicio del metodo");
		//Borra el pedido indicado por parametro
		super.delete(o);
		COSLOG.debug("Fin del metodo");
		
	}

	@Override
	public void calculateOrder(Order o) {
		
	}

}
