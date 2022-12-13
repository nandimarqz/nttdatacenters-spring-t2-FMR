package com.nttdatacentersspringt2FMR.services;

import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Order;

/**
 * Implementacion general del servicio de calculo de pedido
 * @author nandi
 *
 */
@Service
public class CalculateOrderServiceImpl extends OrderManagmentServiceImpl implements CalculateOrderServiceI{


	@Override
	public void cancelOrder(Order o) {
		
		//Borra el pedido indicado por parametro
		super.delete(o);
		
	}

	@Override
	public void calculateOrder(Order o) {
		
	}

}
