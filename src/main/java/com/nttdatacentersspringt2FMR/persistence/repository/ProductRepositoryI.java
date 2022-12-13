package com.nttdatacentersspringt2FMR.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdatacentersspringt2FMR.persistence.Product;

/**
 * Repositorio de los productos
 * 
 * @author nandi
 *
 */
@Repository
public interface ProductRepositoryI extends JpaRepository<Product, Long>{

	/**
	 * Devuelve un producto con la referencia pasada por parametro
	 * 
	 * @param reference
	 * @return Product
	 */
	public Product findByReference(String reference);
}
