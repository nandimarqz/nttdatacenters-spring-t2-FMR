package com.nttdatacentersspringt2FMR.services;

import com.nttdatacentersspringt2FMR.persistence.Order;

/**
 * Interfa del servicio de calculo de pedidos
 * 
 * @author nandi
 *
 */
public interface CalculateOrderServiceI {

	/**
	 * Borra el pedido pasado por parametro
	 * 
	 * @param o
	 */
	public void cancelOrder(Order o);
	
	/**
	 * Calcula el precio total del pedido pasado por parametro
	 * 
	 * @param o
	 */
	public void calculateOrder(Order o);
	
}
