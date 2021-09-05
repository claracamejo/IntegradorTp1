package model;

/**
 * Objeto base de la relacion Factura_Producto para utilizar con java
 */

public class Factura_Producto {

	private int idFactura;
	private int idProducto;
	private int cantidad;
	
	public Factura_Producto(int idFactura, int idProducto, int cantidad) {
		super();
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Factura_Producto [idFactura: " + idFactura + ", idProducto: " + idProducto + ", cantidad: " + cantidad + "]";
	}

}
