package modelo;

import java.util.List;

import java.util.ArrayList;

public class Combo implements IProducto{
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private double descuento;
	
	private String nombreCombo;
	
	private List<IProducto> itemsCombo;
	
	private int cal;
	
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
	
	public Combo(String elNombre, double elDescuento, int calorias)
	{
		nombreCombo = elNombre;
		descuento = elDescuento;
		itemsCombo = new ArrayList<>();
		cal = calorias;
	}

	public double getDescuento() {
		return descuento;
	}


	public String getNombre() {
		return nombreCombo;
	}
	public List<IProducto> getitemsCombo(){
		return itemsCombo;
	}
	
	public void agregarItemACombo(IProducto itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	public int getPrecio()
	{
		int suma = 0;
		for (IProducto producto: itemsCombo) 
		{
			int precioProducto = producto.getPrecio();
			double precioDouble = precioProducto;
			int precio = (int)((precioDouble)*(1.0-descuento));
			suma = suma + precio;
		}
		return suma;
	}
	
	public String generarTextoFactura() {
		String texto = nombreCombo + "\t" + Integer.toString(getPrecio());
		return texto;
	}

	public int getCal() {
		return cal;
	}
	
}
