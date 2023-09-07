package modelo;

import java.util.List;
import java.util.ArrayList;

import modelo.ProductoMenu;
import modelo.Ingrediente;


public class ProductoAjustado implements IProducto{
	// ************************************************************************
	// Atributos
	// ************************************************************************
		
	private ProductoMenu productoBase;
	private int cal;
	private List<Ingrediente> agregados;
	private List<Ingrediente> eliminados;
	
	// ************************************************************************
	// Getters y setters
	// ************************************************************************
	
	public ProductoAjustado(ProductoMenu base)
	{
		productoBase = base;
		agregados = new ArrayList<>();
		eliminados = new ArrayList<>();
		cal = productoBase.getCal();
	}
	public void agregarIngredienteAProducto(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}
	public void eliminarIngredienteAProducto(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}
	public String getNombre() {
		String nombreBase = productoBase.getNombre();
		String nombresAgregados = "";
		if (!(agregados.isEmpty())) 
		{
		nombresAgregados = " con adición de";
		for (Ingrediente agregado: agregados) 
		{
			nombresAgregados = nombresAgregados+ " " + agregado.getNombre()+ ",";
			}
		if (nombresAgregados.endsWith(",")) 
		{
			nombresAgregados = nombresAgregados.substring(0, nombresAgregados.length() - 1);
			}
		}
		String nombresEliminados = "";
		if (!(eliminados.isEmpty())) 
		{
		nombresEliminados = " sin";
		for (Ingrediente eliminado: eliminados) 
		{
			nombresEliminados = nombresEliminados+ " " + eliminado.getNombre()+ ",";
			}
		if (nombresEliminados.endsWith(",")) 
		{
			nombresEliminados = nombresEliminados.substring(0, nombresEliminados.length() - 1);
			}
		}
		String textoFinal = nombreBase + nombresAgregados + nombresEliminados;
		return textoFinal;
	}
	public int getPrecio() {
		int precioBase = productoBase.getPrecio();
		int precioAgregado = 0;
		for (Ingrediente agregado: agregados)
		{
			precioAgregado = precioAgregado + agregado.getCostoAdicional();
		}
		int precioFinal = precioBase + precioAgregado;
		return precioFinal;
	}
	public String generarTextoFactura()
	{
		String nombre = getNombre();
		String precio = Integer.toString(getPrecio());
		String texto = nombre + "\t" + precio;
		return texto;
		
	}
	public int getCal()
	{
		return cal;
	}
	
}
