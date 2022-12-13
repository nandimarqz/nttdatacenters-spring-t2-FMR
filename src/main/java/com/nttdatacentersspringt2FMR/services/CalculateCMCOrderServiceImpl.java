package com.nttdatacentersspringt2FMR.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Order;
import com.nttdatacentersspringt2FMR.persistence.Product;

/**
 * Implementacion del servicio de calculo de pedidos en Ceuta, Melilla y Canarias
 * 
 * @author nandi
 *
 */
@Service("CMCOrder")
public class CalculateCMCOrderServiceImpl extends CalculateOrderServiceImpl implements OrderManagmentServiceI{
	
	@Autowired
	ProductManagmentServiceI pms;
	
	@Override
	public void calculateOrder(Order o) {
		
		// Si el pedido es distinto a null y su id tambien entra en la condicion
		if(o != null && o.getId() == null) {
			
			Double price = 0D;
			
			// Recorre los productos del pedido
			for(Product p : o.getProducts()) {
				
				// Va sumando el precio de los productos
				price += p.getGrossPrice();
				
			}
			
			// se calcula el impuesto
			price = ((price * 4) / 100) + price;
			
			// Seteamos el total del pedido con el precio calculado
			o.setFullPrice(price);
			
			// Recorre los productos del pedido de nuevo y añade el pedido a su lista
			for(Product p : o.getProducts()) {
				
				if(p.getOrders() == null) {
					
					List<Order> orders = new ArrayList<>();
					orders.add(o);
					
					p.setOrders(orders);
					
				}else {
					
					p.getOrders().add(o);
					pms.update(p);
					
				}
				
				
			}
			
			super.add(o);
			
		}
	}

}
