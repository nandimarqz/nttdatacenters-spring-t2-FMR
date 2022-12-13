package com.nttdatacentersspringt2FMR.persistence;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Entidad que representa a los clieentes en BBDD
 * 
 * @author nandi
 *
 */
@Entity
@Table(name = "NTTDATA_SPRING_T2_CLIENTE")
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private Long customerId;

	/** Nombre del cliente */
	private String name;

	/** Primer apellido del cliente */
	private String firstSurname;

	/** Segundo apellido del cliente */
	private String secondSurname;

	/** DNI del cliente */
	private String dni;

	/** Lista de pedidos del cliente */
	private List<Order> orders;

	/**
	 * Devuelve el ID del clietne
	 * 
	 * @return Long
	 */
	@Column(name = "CUSTOMER_ID")
	@Id
	@GeneratedValue(generator = "NNTDATA_SEC")
	@SequenceGenerator(name = "NNTDATA_SEC", sequenceName = "NNTDATA_CUSTOMER_SEC", allocationSize = 1)
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Establece el ID del cliente por el id pasado por parametro
	 * 
	 * @param id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Devuelve el nombre del cliente
	 * 
	 * @return String
	 */
	@Column(name = "NOMBRE")
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del cliente por el nombre pasado por parametro
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el primer apellido del cliente
	 * 
	 * @return String
	 */
	@Column(name = "APELLIDO1")
	public String getFirstSurname() {
		return firstSurname;
	}

	/**
	 * Establece el primer apellido del cliente por el apellido pasado por parametro
	 * 
	 * @param firstSurname
	 */
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	/**
	 * Devuelve el segundo apellido del cliente
	 * 
	 * @return String
	 */
	@Column(name = "APELLIDO2")
	public String getSecondSurname() {
		return secondSurname;
	}

	/**
	 * Establece el segundo apellido del cliente por el apellido pasado por
	 * parametro
	 * 
	 * @param secondSurname
	 */
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	/**
	 * Devuelve el DNI del cliente
	 * 
	 * @return String
	 */
	@Column(name = "DNI", unique = true)
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del cliente por el DNI pasado por parametro
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Devuelve una lista con los pedidos del cliente
	 * 
	 * @return lista de pedidos
	 */
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Establece la lista de pedidos por la lista pasado por parametro
	 * 
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	@Transient
	public Long getId() {
		return this.getCustomerId();
	}

}
