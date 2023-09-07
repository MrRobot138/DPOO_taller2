package modelo;


public class ProductoMenu implements IProducto{
	// ************************************************************************
	// ************************************************************************
		
	private int precioBase;
		
	private String nombre;
		
		
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
		
	public ProductoMenu(String elNombre, int elPrecio)
	{
		nombre = elNombre;
		precioBase = elPrecio;
	}

	public int getPrecio() 
	{
		return precioBase;
	}


	public String getNombre() 
	{
		return nombre;
	}
		
		
	public String generarTextoFactura() 
	{
		String texto = nombre + "\t" + Integer.toString(precioBase);
		return texto;
	}
}
