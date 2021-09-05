package dao.derby;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DAO;
import dao.DAOManager;
import model.Cliente;
import model.Factura;

public class DerbyManager implements DAOManager {

	private String driver;
	private Connection conn;

	/**
	 * Constructor que establece la conexion a la bd, dado un nombre por parametro
	 * @param name
	 * @throws SQLException
	 */
	public DerbyManager(String name) throws SQLException  {
		this.driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
			this.conn = DriverManager.getConnection("jdbc:derby:" + name + ";create=true");
	}

	/**
	 * Crea la tabla de Cliente, crea un objeto de ClienteDerby y le pasa la conexion 
	 */
	public DAO<Cliente, Integer> getClienteDAO() {
		String query = "CREATE TABLE cliente( "
				+ " idCliente INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " nombre VARCHAR(200), email VARCHAR(255), PRIMARY KEY (idCliente))";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("La tabla Cliente ya existe.");
		}
		return new ClienteDerby(conn);
	}

	/**
	 * Crea la tabla de Factura, crea un objeto de FacturaDerby y le pasa la conexion 
	 */
	@Override
	public DAO<Factura, Integer> getFacturaDAO() {
		String query = "CREATE TABLE factura ("
				+ "    idFactura INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				+ "    idCliente INT, " + "    PRIMARY KEY (idFactura),"
				+ "	 FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente))";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("La tabla factura ya existe.");
		}
		return new FacturaDerby(conn);
	}

	/**
	 * Crea la tabla de producto, crea un objeto de ProductoDerby y le pasa la conexion
	 */
	@Override
	public ProductoDerby getProductoDAO() {
		String query = "CREATE TABLE producto( "
				+ "idProducto INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "nombre VARCHAR(200), valor FLOAT, PRIMARY KEY (idProducto))";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("La tabla producto ya existe.");
		}
		return new ProductoDerby(conn);
	}

	/**
	 * Crea la tabla de FacturaProducto, esta no tiene conexion o DAO ya que es una tabla relacional many to many
	 * se puede acceder a ella a traves de Factura
	 */
	@Override
	public void crearTablaFacturaProducto() {
		String query = "CREATE TABLE factura_producto(" + "idFactura INT," + "idProducto INT," + "cantidad INT,"
				+ "CONSTRAINT factura_producto_pk PRIMARY KEY (idFactura, idProducto),"
				+ "CONSTRAINT factura_producto_fk1 FOREIGN KEY (idFactura)" + "REFERENCES factura (idFactura),"
				+ "CONSTRAINT factura_producto_fk2 FOREIGN KEY (idProducto)" + "REFERENCES producto (idProducto))";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("La relacion entre factura y producto ya existe.");
		}
	}

	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
