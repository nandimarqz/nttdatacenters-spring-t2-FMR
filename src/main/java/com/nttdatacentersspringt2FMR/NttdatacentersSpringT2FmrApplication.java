package com.nttdatacentersspringt2FMR;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdatacentersspringt2FMR.persistence.City;
import com.nttdatacentersspringt2FMR.persistence.Customer;
import com.nttdatacentersspringt2FMR.persistence.Order;
import com.nttdatacentersspringt2FMR.persistence.Product;
import com.nttdatacentersspringt2FMR.services.CalculateOrderServiceI;
import com.nttdatacentersspringt2FMR.services.CustomerManagmentServiceI;
import com.nttdatacentersspringt2FMR.services.OrderManagmentServiceI;
import com.nttdatacentersspringt2FMR.services.ProductManagmentServiceI;

/**
 * Clase principal
 * 
 * @author nandi
 *
 */
@SpringBootApplication
public class NttdatacentersSpringT2FmrApplication implements CommandLineRunner {

	private static final String ENDOFMETHOD = "Fin del metodo";
	private static final String BEGINNINGOFTHEMETHOD = "Inicio del metodo";
	/** Campos de auditoria */
	static String updatedUser = "FMR";
	static Date updatedDate = new Date();

	/** Servicio de gestion de productos */
	@Autowired
	ProductManagmentServiceI pms;

	/** Servicio de gestion de clientes */
	@Autowired
	CustomerManagmentServiceI cms;

	/** Servicio de calculo peninsular */
	@Autowired
	@Qualifier("PenOrder")
	CalculateOrderServiceI cpos;

	/** Servicio de calculo de ceuta, melilla, canarias */
	@Autowired
	@Qualifier("CMCOrder")
	CalculateOrderServiceI ccmcos;

	/** Servicio de gestion de los pedidos */
	@Autowired
	@Qualifier("General")
	OrderManagmentServiceI oms;
	
	/** Logger para la clase */
	final Logger MAINLOG = LoggerFactory.getLogger(NttdatacentersSpringT2FmrApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NttdatacentersSpringT2FmrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {
			MAINLOG.debug(BEGINNINGOFTHEMETHOD);
			createProducts();

			int option = 0;
			do {

				Customer c = this.logInOrSingUp(sc);
				
				if(c != null) {
					option = this.menu(c, sc);
				}else {
					
					option = -2;
					
				}
				
			} while (option == -1);

		}
		MAINLOG.debug(ENDOFMETHOD);

	}

	/**
	 * Devuelve el cliente que ha iniciado sesion
	 * 
	 * @param sc
	 * @return Customer
	 */
	public Customer logInOrSingUp(Scanner sc) {
		
		MAINLOG.debug(BEGINNINGOFTHEMETHOD);

		Customer c = null;

		boolean log = false;

		String menu = """

				¿Que desa realizar?
				1.-Iniciar Sesion
				2.-Registarse
				-1 Para salir
				""";

		System.out.println(menu);// Muestra el menu
		int opcion = sc.nextInt();// Recoge la opcion escogida

		// Mientras que la opcion sea distinta a -1 y log es falso entra en la condicion
		while (opcion != -1 && !log) {

			switch (opcion) {

			case 1:

				System.out.println("DNI:"); // pide el dni
				String dni = sc.next(); // Recoge el dni

				// Si existe un cliente con ese dni entra en la condicion si no le envia al caso
				// 2
				if (Boolean.TRUE.equals(cms.existByDni(dni))) {

					// Guarda el cliente con el dni recogido
					c = cms.findByDni(dni);
					// log a true
					log = true;

				} else {

					System.out.println("No existe un cliente con el siguiente DNI: " + dni);
					opcion = 2;

				}

				break;

			case 2:

				System.out.println("Introduzca su nombre");// Le pide el nombre
				sc.nextLine();
				String name = sc.nextLine();// Lo recoge

				System.out.println("Introduzca su primer apellido");// Le pide el primer apellido
				String firstSurname = sc.nextLine();// Lo recoge

				System.out.println("Introduzca su segundo apellido");// Le pide el segundo apellido
				String secondSurname = sc.nextLine();// lo recoge

				System.out.println("Introduzca su DNI");// Le pide el dni
				dni = sc.nextLine();// lo recoge

				// Inyecta un clietne
				c = new Customer();

				// Se le setean los atributos
				c.setName(name);
				c.setFirstSurname(firstSurname);
				c.setSecondSurname(secondSurname);
				c.setDni(dni);
				c.setUpdatedDate(updatedDate);
				c.setUpdatedUser(updatedUser);
				c.setOrders(new ArrayList<>());

				// Añade el cliente
				cms.add(c);

				// Recogemos el cliente con su dni
				c = cms.findByDni(dni);

				// log igual a true
				log = true;

				break;

			default:

				System.out.println("La opcion que ha escogido no existe pruebe con 1 , 2 o -1");

				break;

			}

		}
		
		MAINLOG.debug(ENDOFMETHOD);

		return c;

	}

	/**
	 * Muestra el menu con opciones a realizar
	 * 
	 * @param c
	 * @param sc
	 * @return Integer
	 */
	public int menu(Customer c, Scanner sc) {
		
		MAINLOG.debug(BEGINNINGOFTHEMETHOD);

		String menu = """

				Escriba el numero de la opcion que quiere realizar
				1.-Realizar un pedido
				2.-Cancelar pedido
				3.-Mostrar todos los pedidos realizados por el cliente
				-1 Para salir
				""";

		System.out.println(menu);// Muestra el menu
		int opcion = sc.nextInt();// Recoge la opcion

		// Mientras la opcion sea distinta a -1 entra en la condicion
		while (opcion != -1) {

			switch (opcion) {

			case 1:

				char confirm = 'x';

				// Inyecta un pedido
				Order or = new Order();

				// Se setean sus atributos
				or.setCustomer(c);
				or.setUpdatedDate(updatedDate);
				or.setUpdatedUser(updatedUser);
				or.setReference("PED-" + (oms.countOrders() + 1));
				or.setProducts(new ArrayList<>());

				// Mientras que confirm sea distitno a N entra en la condicion
				while (confirm != 'N') {

					this.showProducts();// muestra los productos
					long product = sc.nextLong();// Guarda la opcion

					Product p = pms.findById(product);// Busca el producto con el id recogido y lo guarda en una
														// variable

					or.getProducts().add(p);// Añade el producto a la lista de productos del pedido

					// Pregunta si quiere añadir otro producto
					System.out.println("Desea añadir otro producto? S o N ");
					confirm = Character.toUpperCase(sc.next().charAt(0));// Recoge la letra

				}

				boolean city = false;

				// Mientras que ciudad sea falso entra en la condicion
				while (!city) {

					System.out.println("A que ciudad hay que enviar el pedido");
					String ciudades = """
							1.-Melilla
							2.-Canarias
							3.-Ceuta
							4.-Peninsular
							""";

					System.out.println(ciudades);// Muestra las ciudades
					opcion = sc.nextInt();// Recoge la opcion seleccionada

					switch (opcion) {

					case 1:

						// Se seta la ciudad a melilla
						or.setCity(City.MELILLA);
						city = true;

						break;

					case 2:

						// Se seta la ciudad a canarias
						or.setCity(City.CANARIAS);
						city = true;

						break;

					case 3:

						// Se seta la ciudad a ceuta
						or.setCity(City.CEUTA);
						city = true;

						break;

					case 4:

						// Se seta la ciudad a peninsula
						or.setCity(City.PENINSULA);
						city = true;

						break;

					default:

						System.out.println("La opcion escogida no existe");

						break;

					}

				}

				// Si la ciudad del producto es igual a peninsula entra en la condicion si no va
				// al else
				if (or.getCity().equals(City.PENINSULA)) {

					// Calcula el pedido con el servicio peninsular
					cpos.calculateOrder(or);

				} else {

					// Calcula el pedido con el servicio de canarias, ceuta y melilla
					ccmcos.calculateOrder(or);

				}

				break;

			case 2:

				System.out.println("Introduzca la referencia del pedido"); // Pide la referencia del pedido
				String ref = sc.next();// guarda al referencia

				// Si lo que devuelve el metodo es distinto a null es que existe un pedido con
				// esa referencia
				if (oms.findByReference(ref) != null) {

					// Guarda el pedido en una variable
					Order o = oms.findByReference(ref);
					// Borra el pedido
					oms.delete(o);

				} else {

					System.out.println("No existe pedido con la referencia introducida");

				}

				break;

			case 3:

				// Recorre todos los pedido del cliente pasador por parametro y los muestra
				for (Order o : oms.findByCustomer(c)) {

					System.out.println("Referencia: " + o.getReference() + "Precio total: " + o.getFullPrice());

				}

				break;

			default:

				System.out.println("La opcion seleccionada no existe");

				break;

			}
			
			System.out.println(menu);//Muestra el menu
			opcion = sc.nextInt();//Recoge la opcion

		}
		
		MAINLOG.debug(ENDOFMETHOD);

		return opcion;

	}

	/**
	 * Muestra todos los productos
	 * 
	 */
	public void showProducts() {

		MAINLOG.debug(BEGINNINGOFTHEMETHOD);
		
		for (Product p : pms.findAll()) {

			System.out.println(p.getId() + ". Nombre: " + p.getName() + "Precio bruto: " + p.getGrossPrice());

		}

		MAINLOG.debug(ENDOFMETHOD);
	}

	/**
	 * Crea productos y los inserta en BBDD
	 */
	public void createProducts() {
		
		MAINLOG.debug(BEGINNINGOFTHEMETHOD);

		Product p1 = new Product();
		p1.setName("Logitech g 513 Carbon");
		p1.setUpdatedDate(updatedDate);
		p1.setUpdatedUser(updatedUser);
		p1.setReference("TECH-1");
		p1.setGrossPrice(120.20);

		Product p2 = new Product();
		p2.setName("Logitech g 915 TKL");
		p2.setUpdatedDate(updatedDate);
		p2.setUpdatedUser(updatedUser);
		p2.setReference("TECH-2");
		p2.setGrossPrice(200.00);

		Product p3 = new Product();
		p3.setName("Logitech g 502 Hero SE");
		p3.setUpdatedDate(updatedDate);
		p3.setUpdatedUser(updatedUser);
		p3.setReference("TECH-3");
		p3.setGrossPrice(80.50);

		Product p4 = new Product();
		p4.setName("Logitech MX Mechanical");
		p4.setUpdatedDate(updatedDate);
		p4.setUpdatedUser(updatedUser);
		p4.setReference("TECH-4");
		p4.setGrossPrice(105.00);

		Product p5 = new Product();
		p5.setName("Logitech g Blue Sona");
		p5.setUpdatedDate(updatedDate);
		p5.setUpdatedUser(updatedUser);
		p5.setReference("TECH-5");
		p5.setGrossPrice(359.00);

		Product p6 = new Product();
		p6.setName("Logitech g Litra Beam");
		p6.setUpdatedDate(updatedDate);
		p6.setUpdatedUser(updatedUser);
		p6.setReference("TECH-6");
		p6.setGrossPrice(125.30);

		Product p7 = new Product();
		p7.setName("Logitech g Pro X");
		p7.setUpdatedDate(updatedDate);
		p7.setUpdatedUser(updatedUser);
		p7.setReference("TECH-7");
		p7.setGrossPrice(165.00);

		Product p8 = new Product();
		p8.setName("Logitech g 512 Carbon");
		p8.setUpdatedDate(updatedDate);
		p8.setUpdatedUser(updatedUser);
		p8.setReference("TECH-8");
		p8.setGrossPrice(120.20);

		Product p9 = new Product();
		p9.setName("Logitech g Streamcam");
		p9.setUpdatedDate(updatedDate);
		p9.setUpdatedUser(updatedUser);
		p9.setReference("TECH-9");
		p9.setGrossPrice(165.00);

		Product p10 = new Product();
		p10.setName("Logitech g 480");
		p10.setUpdatedDate(updatedDate);
		p10.setUpdatedUser(updatedUser);
		p10.setReference("TECH-10");
		p10.setGrossPrice(47.99);

		pms.add(p1);
		pms.add(p2);
		pms.add(p3);
		pms.add(p4);
		pms.add(p5);
		pms.add(p6);
		pms.add(p7);
		pms.add(p8);
		pms.add(p9);
		pms.add(p10);
		
		MAINLOG.debug(ENDOFMETHOD);

	}

}
