package dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import dao.DAOException;
import model.Factura;
import model.Factura_Producto;

public class FacturaDerby implements DAO<Factura, Integer>{
		
	private Connection conn;	
	
	/**
	 * A partir de la conexion que se creo en DerbyManager se realizan las consultas en los otros metodos
	 * @param conn
	 */
	public FacturaDerby(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	/**
	 * a partir de un objeto de factura se realiza una query y guarda los datos en la bd
	 */
	public void insert(Factura p) throws DAOException {
		PreparedStatement stat = null;
		String insert = "INSERT INTO factura(idCliente) VALUES(?)";
		
		try {
			stat = conn.prepareStatement(insert);
			stat.setInt(1, p.getIdCliente());
			if (stat.executeUpdate() == 0)
				throw new DAOException("Maybe not saved correctly.");
			
		} catch (SQLException e) {
			throw new DAOException("Error en SQL", e);
			
		}
	}

	
	/**
	 * metodo privado que convierte un ResultSet en objetos de la clase factura
	 */
	private List<Factura> convert(ResultSet rs) throws SQLException {
		List<Factura> facturas = new ArrayList<Factura>();
		
		while(rs.next()) {
			int idFactura = rs.getInt("idFactura");
			int idCliente = rs.getInt("idCliente");
			Factura factura = new Factura(idFactura, idCliente);
			facturas.add(factura);
		}
		return facturas;
	}
	
	
	/**
	 * trae todos los elementos que esten en la tabla factura.
	 */
	@Override
	public List<Factura> getElements() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String getAll = "SELECT idFactura, idCliente FROM factura";
		
		try {
			stat = conn.prepareStatement(getAll);
			rs = stat.executeQuery();
			return convert(rs);
		} catch (SQLException e) {
			throw new DAOException("Error SQL", e);
		}
	}
	
	
	/**
	 * A partir de objetos de facturaProducto, guarda la informacion en la bd
	 * la insercion de datos se crea en esta clase ya que entedemos que factura_Producto
	 * es una tabla relacional many to many entre Factura y Producto, por lo que una factura puede tener
	 * muchos productos, y un producto estar en muchas facturas, y que esta relacion se realiza cuando
	 * se agregar productos a una factura.
	 */
	public void insertFacturaProducto(Factura_Producto fp) throws DAOException {
		PreparedStatement stat = null;
		String insertFP = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)";
		try {
			stat = conn.prepareStatement(insertFP);
			stat.setInt(1, fp.getIdFactura());
			stat.setInt(2, fp.getIdProducto());
			stat.setInt(3, fp.getCantidad());
			if (stat.executeUpdate() == 0)
				throw new DAOException("Maybe not saved correctly.");
			
		} catch (SQLException e) {
			throw new DAOException("Error SQL", e);
		}
	}
	
	
}
