package dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import dao.DAOException;
import model.Cliente;

/**
 * Esta Clase conecta con la base de datos a
 * todas las querys que esten relacionadas con
 * informacion de clientes
 */
public class ClienteDerby implements DAO<Cliente, Integer> {
	
	private Connection conn;
	
	/**
	 * A partir de la conexion que se creo en DAOManagerDerby se realizan las consultas en los otros metodos
	 * @param conn
	 */
	public ClienteDerby(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	/**
	 * A partir de un objeto cliente guarda los datos del mismo en la bd
	 */
	public void insert(Cliente c) throws DAOException {
		PreparedStatement stat = null;
		
		String insert = "INSERT INTO cliente(nombre, email) VALUES(?, ?)";
		
		try {
			stat = conn.prepareStatement(insert);
			stat.setString(1, c.getNombre());
			stat.setString(2, c.getEmail());
			if (stat.executeUpdate() == 0)
				throw new DAOException("Maybe not saved correctly.");
			
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
		}
	}
	
	
	/**
	 * Consulta a la bd de todos los objetos que tenga la tabla
	 * utiliza el metodo convert para convertir resultados de la consulta en objetos de java
	 */
	public List<Cliente> getElements() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String getAll = "SELECT idCliente, nombre, email FROM cliente";
		
		try {
			stat = conn.prepareStatement(getAll);
			rs = stat.executeQuery();
			return convert(rs);
		} catch (SQLException e) {
			throw new DAOException("Error SQL", e);
		}
	}
	
	
	/**
	 * Metodo interno de la clase que convierte los resultados de una consulta a la bd en objetos para poder utilizarlos con java
	 * @param rs
	 * @throws SQLException
	 */
	private List<Cliente> convert(ResultSet rs) throws SQLException {
		List<Cliente> personas = new ArrayList<Cliente>();
		
		while(rs.next()) {
			String nombre = rs.getString("nombre");
			String email = rs.getString("email");
			Cliente persona = new Cliente(nombre, email);
			persona.setIdCliente(rs.getInt("idCliente")); 
			personas.add(persona);
		}
		return personas;
	}
	
	/**
	 * Devuelve una lista de clientes ordenada por a cual se le facturo mas.
	 * Consulta a la db y devuelve el id del cliente, nombre, email y ademas 
	 * la facturacion
	 *  @return 	List<Cliente> */
	public List<Cliente> listaClientesOrdenada () throws Exception {

		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Cliente> clientesOrdenado = new ArrayList<Cliente>();
		
		String getAll = "SELECT fac.idCliente, cli.nombre, cli.email, sum(facProd.cantidad * prod.valor) AS facturacion "
			      + "FROM factura fac "
			      + "JOIN factura_producto facProd ON (facProd.idFactura = fac.idFactura) "
			      + "JOIN producto prod ON (prod.idProducto = facProd.idProducto) "
			      + "JOIN cliente cli ON (fac.idCliente = cli.idCliente) "
			      + "GROUP BY fac.idCliente, cli.nombre, cli.email "
			      + "ORDER BY facturacion DESC";


		try {
			stat = conn.prepareStatement(getAll);
			rs = stat.executeQuery();
			while (rs.next()) {
				float fac = rs.getFloat("facturacion");
				Cliente cliente = new Cliente(rs.getInt("idCliente"),rs.getString("nombre"),rs.getString("email"));
				cliente.setFacturacion(fac);
				clientesOrdenado.add(cliente);
			}
			return clientesOrdenado;
			
		} catch (SQLException e) {
			throw new DAOException("Error SQL", e);
		}




	}

}
