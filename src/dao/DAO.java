package dao;

import java.util.List;

import model.Factura_Producto;

/**
 * Interfaz que declara los metodos minimos que tienen que tener las clases que se conecten a las distintas bases de datos.
 *
 * @param <T> una Clase de tipo DAO
 * 
 * La K la ponemos por que sino no nos deja devolver un objetoDerby en DerbyManager cuando obtenemos las conexiones de las tablas.
 */
public interface DAO<T, K> {

	public void insert(T p) throws DAOException;

	public List<T> getElements() throws DAOException;

}
