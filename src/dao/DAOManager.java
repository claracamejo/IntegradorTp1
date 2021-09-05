package dao;

import model.Cliente;
import model.Factura;
import model.Producto;

/**
 * Esta interfaz declara los metodos que se deben utilizar en los DAOs de las distintas bases de datos
 */

public interface DAOManager extends AutoCloseable {
	
	DAO<Cliente, Integer> getClienteDAO();
	
	DAO<Factura, Integer> getFacturaDAO();
	
	DAO<Producto, Integer> getProductoDAO();
	
	void crearTablaFacturaProducto();

	void close();
	
}
