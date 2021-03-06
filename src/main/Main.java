package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dao.DAO;
import dao.DAOException;
import dao.DAOManager;
import dao.derby.ClienteDerby;
import dao.derby.DerbyManager;
import dao.derby.FacturaDerby;
import dao.derby.ProductoDerby;
import model.Cliente;
import model.Factura;
import model.Factura_Producto;
import model.Producto;

public class Main {

	public static void main(String[] args) {
		/**
		 * Se crean los objetos necesarios para poder utilizar la bd
		 */
		DAOManager DM;
		DAO<Cliente, Integer> clienteDAO;
		DAO<Factura, Integer> facturaDAO;
		DAO<Producto, Integer> productoDAO;
		
		/**
		 * se crea la base de datos, y se establece la conexion
		 */
		try {
			DM = new DerbyManager("DBIntegrador");
		} catch (SQLException e1) {
			System.out.println("No se pudo establecer la conexion con la base de datos.");
			return;
		}
		
	
		try {
			/**
			 * a los objetos creados primero, se los instancia con la bd establecida anteriormente.
			 */
			clienteDAO = DM.getClienteDAO();
			facturaDAO = DM.getFacturaDAO();
			productoDAO = DM.getProductoDAO();
			DM.crearTablaFacturaProducto();
		
			/**
			 * se cargan los datos en la tabla
			 */
			leerCliente(clienteDAO);
			
			ClienteDerby clientes = (ClienteDerby) clienteDAO;
			List<Cliente> listaC = clientes.getElements();
						
			leerFacturas(facturaDAO);
			FacturaDerby facturas = (FacturaDerby) facturaDAO;
			List<Factura> listaF = facturas.getElements();
			
			leerProductos(productoDAO);
		
			leerFacturaProducto(facturaDAO);
			
			/* Ejercicio 3
			 * En este inciso se pide imprimir el producto que mas Recaudo
			 * Para obtener dicho Producto hay que hacer una consulta a la base de datos
			 * llamando al metodo productoQueMasRecaudo que es propio de la clase Producto
			 */
			
			
			ProductoDerby productoDer = (ProductoDerby) productoDAO;
			Producto pro = productoDer.productoQueMasRecaudo();
			System.out.println("Ejercicio 3: ");
			System.out.println("El valor del producto fue modificado por el total recaudado : "+pro.toString());
			System.out.println("");
			System.out.println("Ejercicio 4: ");
			/* Ejercicio 4
			 * En este inciso se pide imprimir una lista de clientes
			 * la cual este ordenada de forma descendente por facturaci?n
			 * llamamos al m?todo que nos devuelve la lista ordenada 
			 * y la imprimimos en el for posterior.
			 */

			ClienteDerby clienteDerby = (ClienteDerby) clienteDAO;
			List<Cliente> listClientesOrd = clienteDerby.listaClientesOrdenada();
			
			for (Cliente cliente : listClientesOrd) {
				System.out.println(cliente.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DM.close();
		}

	}
	
	public static void leerCliente(DAO clienteDAO) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\clientes.csv"));
		for (CSVRecord row : parser) {
			Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			clienteDAO.insert(c);

		}
	}
	
	public static void leerFacturas(DAO facturaDAO) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\facturas.csv"));
		for (CSVRecord row : parser) {
			Factura f = new Factura(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente")));
			facturaDAO.insert(f);

		}
	}
	
	public static void leerFacturaProducto(DAO facturaDAO) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\facturas-productos.csv"));
		for (CSVRecord row : parser) {
			Factura_Producto fp = new Factura_Producto(Integer.parseInt(row.get("idFactura")),
					Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));
			FacturaDerby fd = (FacturaDerby) facturaDAO;
			fd.insertFacturaProducto(fp);
		}
	}
	
	public static void leerProductos(DAO productoDAO) throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\productos.csv"));
		for (CSVRecord row : parser) {
			Producto p = new Producto(row.get("nombre"), Float.parseFloat(row.get("valor")));
			p.setIdProducto(Integer.parseInt(row.get("idProducto")));
			productoDAO.insert(p);

		}
	}

}
