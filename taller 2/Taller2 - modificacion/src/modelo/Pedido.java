package modelo;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pedido {
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccion;
	private List<IProducto> productos;
	
	public Pedido(String elNombreCliente, String laDireccion) 
	{
		nombreCliente = elNombreCliente;
		direccion = laDireccion;
		numeroPedidos++;
		idPedido = numeroPedidos;
		productos = new ArrayList<>();
	}
	public int getIdPedido()
	{
		return idPedido;
	}
	public String caloriasTotales()
	{
		int calorias = 0;
		for (IProducto producto: productos)
		{
			int precioProd = producto.getCal();
			calorias = calorias + precioProd;
			
		}
		String calTotal = String.valueOf(calorias);
		return calTotal;
	}
	public void agregarProducto(IProducto nuevoItem)
	{
		productos.add(nuevoItem);
	}
	public int getPrecioNetoPedido()
	{
		int precio = 0;
		for (IProducto producto: productos)
		{
			int precioProd = producto.getPrecio();
			precio = precio + precioProd;
		}
		return precio;
	}
	public int getPrecioIVAPedido()
	{
		int precioNeto = getPrecioNetoPedido();
		int precioIva = (int)(precioNeto*0.19);
		return precioIva;
	}
	public int getPrecioTotalPedido()
	{
		int precioNeto = getPrecioNetoPedido();
		int precioIva = getPrecioIVAPedido();
		int precioTotal = precioNeto + precioIva;
		return precioTotal;
	}
	public String generarTextoFactura()
	{
		String textoFactura = "";
		for (IProducto producto: productos)
		{
			String textoProd = producto.generarTextoFactura();
			textoFactura = textoFactura +  "\n" + textoProd;
		}
		String calorias = caloriasTotales();
		textoFactura ="\n"+"Calorias Totales: \t" + calorias +"\nPRODUCTOS: "+"\n" + textoFactura +"\n_________________________________________________\n" +"\n" +"NOMBRE CLIENTE:"+"\t"+nombreCliente+"\n"+"DIRECCION CLIENTE:"+"\t"+direccion +"\n_________________________________________________\n"+"\n" + "PRECIO NETO TOTAL" + "\t" + getPrecioNetoPedido() + "\n" + "PRECIO IVA TOTAL" + "\t" + getPrecioIVAPedido() + "\n" + "PRECIO TOTAL" + "\t" + getPrecioTotalPedido();
		
		return textoFactura;
	}
	public void guardarFactura(boolean igual) throws IOException
	{
		String textoFinal = generarTextoFactura();
		if (igual == true) 
		{
			textoFinal = "HAY UN PEDIDO IGUAL \n" + textoFinal;
		}
		File path = new File("./Facturas/" + idPedido + ".txt");
		FileWriter writer = new FileWriter(path);
		writer.write(textoFinal);
		writer.flush();
		writer.close();
	}
	public boolean equals(Pedido pedidoNuevo)
	{
		boolean igual = false;
		if (this.caloriasTotales().equals(pedidoNuevo.caloriasTotales())) {
			if (this.getPrecioTotalPedido() == pedidoNuevo.getPrecioTotalPedido())
			{
				igual = true;
			}
		}
		return igual;
	}
}
