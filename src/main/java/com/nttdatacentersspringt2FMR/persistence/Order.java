package com.nttdatacentersspringt2FMR.persistence;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Entidad que representa a los pedidos en BBDD
 * 
 * @author nandi
 *
 */
@Entity
@Table(name = "NTTDATA_SPRING_T2_PEDIDO")
public class Order extends AbstractEntity {

	/** Id del pedido */
	private Long orderId;

	/** Precio total del pedido */
	private Double fullPrice;

	/** Referencia del pedido */
	private String reference;

	/** Cliente que realiza el pedido */
	private Customer customer;

	/** Ciudad de envio del pedido */
	private City city;

	/** lista de productos del pedido */
	private List<Product> products;

	private static final long serialVersionUID = 1L;

	/**
	 * Devuelve el id del pedido
	 * 
	 * @return Long
	 */
	@Column(name = "ORDER_ID")
	@Id
	@GeneratedValue(generator = "NNTDATA_SEC")
	@SequenceGenerator(name = "NNTDATA_SEC", sequenceName = "NNTDATA_ORDER_SEC", allocationSize = 1)
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * Establece el id del pedido por el id pasado por parametro
	 * 
	 * @param orderId
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Devuelve el precio total del pedido
	 * 
	 * @return Double
	 */
	@Column(name = "FULL_PRICE")
	public Double getFullPrice() {
		return fullPrice;
	}

	/**
	 * Establece el precio total por el precio pasado por parametro
	 * 
	 * @param fullPrice
	 */
	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}

	/**
	 * Devuelve la referencia del pedido
	 * 
	 * @return String
	 */
	@Column(name = "REFERENCE", unique = true)
	public String getReference() {
		return reference;
	}

	/**
	 * Establece la referencia del pedido por la referencia pasada por parametro
	 * 
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Devuelve la ciudad de envio del pedido
	 * 
	 * @return City
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CITY")
	public City getCity() {
		return city;
	}

	/**
	 * Establece la ciudad de envio por la ciudad pasada por parametro
	 * 
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * Devuelve el cliente del pedido
	 * 
	 * @return Customer
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Establece el cliente del pedido por el cliente pasado por parametro
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Devuelve la lista de productos del pedido
	 * 
	 * @return lista de productos
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NTTDATA_SPRING_T2_PEDIDO_PRODUCTO", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Establece la lista de productos por la lista pasada por parametro
	 * 
	 * @param products
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	@Transient
	public Long getId() {
		return this.getOrderId();
	}

}
