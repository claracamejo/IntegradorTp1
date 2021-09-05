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
import model.Cliente;
import model.Factura;
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
			//insertFacturaTable(facturaDAO);
			//insertProductoTable(productoDAO);
			//insertFacturaProductoTable(facturaDAO);
			
			ClienteDerby clientes = (ClienteDerby) clienteDAO;
			List<Cliente> listaC = clientes.getElements();
			
			//for (Cliente cliente : listaC) {
			//	System.out.println(cliente.toString());
			//}
			
			leerFacturas(facturaDAO);
			FacturaDerby facturas = (FacturaDerby) facturaDAO;
			List<Factura> listaF = facturas.getElements();
			
			for (Factura factura : listaF) {
				System.out.println(factura.toString());
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
	
	public static void leerFacturaProducto() throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\facturas-productos.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}
	
	public static void leerProductos() throws Exception {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\productos.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}

}