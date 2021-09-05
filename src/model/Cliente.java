package model;

/**
 * Objeto base de cliente para utilizar con java
 */
public class Cliente {
	private int idCliente;
	private String nombre;
	private String email;
	private float facturacion;

	public Cliente(int idCliente, String nombre, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}

	public Cliente(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(float facturacion) {
		this.facturacion = facturacion;
	}

	@Override
	public String toString() {
		return "[idCliente: " + idCliente + ", nombre: " + nombre + ", email: " + email + ", facturacion: "
				+ facturacion + "]\n";
	}

}
