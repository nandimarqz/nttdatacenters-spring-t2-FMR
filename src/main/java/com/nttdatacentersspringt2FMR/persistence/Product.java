package com.nttdatacentersspringt2FMR.persistence;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Entidad que representa a los productos en la BBDD
 * 
 * @author nandi
 *
 */
@Entity
@Table(name = "NTTDATA_SPRING_T2_PRODUCTO")
public class Product extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** Id del producto */
	private Long productId;

	/** Referencia del producto */
	private String reference;

	/** Nombre del producto */
	private String name;

	/** Precio bruto */
	private Double grossPrice;

	/** Lista de pedidos que contiene el producto */
	private List<Order> orders;

	/**
	 * Devuelve el id del producto
	 * 
	 * @return Long
	 */
	@Column(name = "PRODUCT_ID")
	@Id
	@GeneratedValue(generator = "NNTDATA_SEC")
	@SequenceGenerator(name = "NNTDATA_SEC", sequenceName = "NNTDATA_PRODUCT_SEC", allocationSize = 1)
	public Long getProductId() {
		return productId;
	}

	/**
	 * Establece el id del producto por el id pasado por parametro
	 * 
	 * @param productId
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Devuelve la referencia del producto
	 * 
	 * @return String
	 */
	@Column(name = "REFERENCIA", unique = true)
	public String getReference() {
		return reference;
	}

	/**
	 * Establece la referencia del producto por la referencia pasada por parametro
	 * 
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Devuelve el nombre del producto
	 * 
	 * @return String
	 */
	@Column(name = "NOMBRE")
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del producto por el nombre pasado por parametro
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el precio bruto del producto
	 * 
	 * @return Double
	 */
	@Column(name = "PRECIO_BRUTO")
	public Double getGrossPrice() {
		return grossPrice;
	}

	/**
	 * Establece el precio bruto del prodcuto por el precio pasado por parametro
	 * 
	 * @param grossPrice
	 */
	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}

	/**
	 * Devuelve una lista de los pedidos que contiene el producto
	 * 
	 * @return Lista de pedidos
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Establece la lista de pedidos del producto por la lista pasada por parametro
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	@Transient
	public Long getId() {
		return getProductId();
	}

}
