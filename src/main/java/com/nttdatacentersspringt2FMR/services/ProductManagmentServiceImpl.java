package com.nttdatacentersspringt2FMR.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdatacentersspringt2FMR.persistence.Product;
import com.nttdatacentersspringt2FMR.persistence.repository.ProductRepositoryI;

/**
 * Implementacion del servicio de gestion de los productos
 * 
 * @author nandi
 *
 */
@Service
public class ProductManagmentServiceImpl implements ProductManagmentServiceI {

	/** Repositorio de los productos */
	@Autowired
	ProductRepositoryI pr;
	
	/** Logger para la clase */
	final Logger PMSLOG = LoggerFactory.getLogger(ProductManagmentServiceImpl.class);

	@Override
	public void add(Product p) {

		// Si el producto pasado por parametro es distinto a null y el id es nullo entra
		// en la condicion
		if (p != null && p.getId() == null) {

			PMSLOG.debug("Añadiendo producto");
			pr.save(p);// Guarda el producto en la BBDD

		}

	}

	@Override
	public void delete(Product p) {

		// Si el producto es distinto a null y el id tmb es distinto entra en la
		// condicion
		if (p != null && p.getId() != null) {

			PMSLOG.debug("Borrando producto");
			pr.delete(p);// Borrar el producto

		}

	}

	@Override
	public void update(Product p) {

		// Si el producto es disinto a null y su id tmb es distinto entra en la
		// condicion
		if (p != null && p.getId() != null) {

			//Si existe el producto entra en la condicion
			if (pr.existsById(p.getId())) {

				PMSLOG.debug("Actualizando producto");
				pr.save(p);//Actualiza el producto

			} else {

				System.out.println("El producto que se quiere actualizar no existe");

			}

		}

	}

	@Override
	public List<Product> findAll() {
		
		PMSLOG.debug("Obteniendo todos los productos");
		//Devuelve todos los productos
		return pr.findAll();
	}

	@Override
	public Product findById(Long id) {

		PMSLOG.debug("Obteniendo producto por id");
		//Devuelve el producto con el id pasado por parametro
		return pr.findById(id).get();
	}

}
