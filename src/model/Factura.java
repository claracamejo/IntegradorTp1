package model;

/**
 * Objeto base de factura para utilizar con java
 */

public class Factura {

	private int idFactura;
	private int idCliente;
	
	public Factura(int idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public Factura(int idFactura, int idCliente) {
		this.idCliente = idCliente; 
		this.idFactura = idFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Factura [idFactura: " + idFactura + ", idCliente: " + idCliente + "]";
	}

}
