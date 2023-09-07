package modelo;


public class ProductoMenu implements IProducto{
	// ************************************************************************
	// ************************************************************************
		
	private int precioBase;
		
	private String nombre;
	
	private int cal;
		
		
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
		
	public ProductoMenu(String elNombre, int elPrecio, int calorias)
	{
		nombre = elNombre;
		precioBase = elPrecio;
		cal = calorias;
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

	public int getCal() {
		return cal;
	}
}
