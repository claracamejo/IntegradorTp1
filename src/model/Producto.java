package model;

/**
 * Objeto base de producto para utilizar con java
 */

public class Producto {
	
	private int idProducto;
	private String nombre;
	private float valor;
	
	public Producto(String nombre, float valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public Producto(int idProducto, String nombre, float valor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Producto [idProducto: " + idProducto + ", nombre: " + nombre + ", valor: " + valor + "]";
	}

}
