package modelo;

public interface IProducto {
	public abstract int getPrecio();
	public abstract String getNombre();
	public abstract String generarTextoFactura();
}
