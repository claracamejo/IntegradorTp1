package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) {
		

	}
	
	public static void leerCliente() throws FileNotFoundException, IOException {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\clientes.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}
	
	public static void leerFacturas() throws FileNotFoundException, IOException {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\facturas.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}
	
	public static void leerFacturaProducto() throws FileNotFoundException, IOException {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\facturas-productos.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}
	
	public static void leerProductos() throws FileNotFoundException, IOException {

		CSVParser parser = CSVFormat.DEFAULT.withHeader()
				.parse(new FileReader(".\\src\\data\\productos.csv"));
		for (CSVRecord row : parser) {
			//Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
			//clienteDAO.insert(c);

		}
	}

}
