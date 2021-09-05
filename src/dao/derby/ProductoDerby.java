package dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import dao.DAOException;
import model.Producto;

public class ProductoDerby implements DAO<Producto, Integer> {

	private Connection conn;

	/**
	 * A partir de la conexion que se creo en DAOManagerDerby se realizan las consultas en los otros metodos
	 * @param conn
	 */
	public ProductoDerby(Connection conn) {
		super();
		this.conn = conn;
	}
	/**
	 * a partir de un objeto de producto se realiza una query y guarda los datos en la bd
	 */
	@Override
	public void insert(Producto p) throws DAOException {
		PreparedStatement stat = null;
		String insert = "INSERT INTO producto(nombre, valor) VALUES(?, ?)";
		
		try {
			stat = conn.prepareStatement(insert);
			stat.setString(1, p.getNombre());
			stat.setFloat(2, p.getValor());
			if (stat.executeUpdate() == 0)
				throw new DAOException("Maybe not saved correctly.");
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);

		}
	}
	/**
	 * metodo privado que convierte un ResultSet en objetos de la clase producto
	 */
	private List<Producto> convert(ResultSet rs) throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();

		while (rs.next()) {
			String nombre = rs.getString("nombre");
			Float valor = rs.getFloat("valor");
			Producto producto = new Producto(nombre, valor);
			producto.setIdProducto((rs.getInt("idProducto")));
			productos.add(producto);
		}
		return productos;
	}
	/**
	 * Query que trae todos los datos de la tabla producto
	 * usada generalemnte para corroborar de la carga correcta de los datos
	 */
	@Override
	public List<Producto> getElements() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String getAll = "SELECT idProducto, nombre, valor FROM producto";
		
		try {
			stat = conn.prepareStatement(getAll);
			rs = stat.executeQuery();
			return convert(rs);
		} catch (SQLException e) {
			throw new DAOException("Error SQL", e);
		}
	}


}
