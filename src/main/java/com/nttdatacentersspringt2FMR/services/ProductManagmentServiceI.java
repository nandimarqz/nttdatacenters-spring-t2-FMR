package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import com.nttdatacentersspringt2FMR.persistence.Product;

/**
 * Interfaz del servicio de gestion de productos
 * 
 * @author nandi
 *
 */
public interface ProductManagmentServiceI {
	
	/**
	 * Añade el producto pasado por parametro a la BBDD
	 * 
	 * @param p
	 */
	public void add(Product p);
	
	/**
	 * Borra el producto pasado por parametro BBDD
	 * 
	 * @param p
	 */
	public void delete(Product p);
	
	/**
	 * Actualiza el producto pasado por parametro en la BBDD
	 * 
	 * @param p
	 */
	public void update(Product p);
	
	/**
	 * Devuelve todos los productos de la BBDD
	 * 
	 * @return Lista de productos
	 */
	public List<Product> findAll();
	
	/**
	 * Devuelve el producto con el id pasado por parametro
	 * 
	 * @param id
	 * @return Product
	 */
	public Product findById(Long id);

}
