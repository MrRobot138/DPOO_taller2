package modelo;

public class Ingrediente {
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
	
	private int costoAdicional;
	
	private String nombre;
	
	
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
	
	public Ingrediente(String elNombre, int elPrecio)
	{
		nombre = elNombre;
		costoAdicional = elPrecio;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}


	public String getNombre() {
		return nombre;
	}
	
}