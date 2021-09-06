package dao;

import java.util.List;

import model.Factura_Producto;

/**
 * Interfaz que declara los metodos minimos que tienen que tener las clases que se conecten a las distintas bases de datos.
 * @author Usuario
 *
 * @param <T> una Clase de tipo DAO
 */
public interface DAO<T, K> {

	public void insert(T p) throws DAOException;

	public List<T> getElements() throws DAOException;

}
