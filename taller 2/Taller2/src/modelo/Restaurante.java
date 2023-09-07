package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Combo;
import modelo.ProductoMenu;
import modelo.Ingrediente;
import modelo.Pedido;

public class Restaurante {
	private Map<Integer, Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private List<Combo> combos;
	private List<ProductoMenu> menuBase;
	private List<Ingrediente> ingredientes;
	
	public Restaurante()
	{
		pedidos = new HashMap<>();
		combos = new ArrayList<>();
		menuBase = new ArrayList<>();
		ingredientes = new ArrayList<>();
	}

		
	public void cargarInformacionRestaurante() throws FileNotFoundException, IOException
	{
		Map<String, ProductoMenu> mapaMenu = new HashMap<>();
			
		BufferedReader br = new BufferedReader(new FileReader("./data/menu.txt"));
		String linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			int precioProducto = Integer.parseInt(partes[1]);
			ProductoMenu producto = new ProductoMenu(nombreProducto, precioProducto);
			menuBase.add(producto);
			mapaMenu.put(producto.getNombre(), producto);
			linea = br.readLine();
		}
		br.close();
		BufferedReader brIng = new BufferedReader(new FileReader("./data/ingredientes.txt"));
		String lineaIng = brIng.readLine();
		while (lineaIng != null)
		{
			String[] partesIng = lineaIng.split(";");
			String nombreIngrediente = partesIng[0];
			int costoAdicional = Integer.parseInt(partesIng[1]);
			Ingrediente ingrediente = new Ingrediente(nombreIngrediente ,costoAdicional);
			ingredientes.add(ingrediente);
			lineaIng = brIng.readLine();
		}
		brIng.close();
		BufferedReader brComb = new BufferedReader(new FileReader("./data/combos.txt"));
		String lineaComb = brComb.readLine();
		while (lineaComb != null)
		{
			String[] partesComb = lineaComb.split(";");
			String nombreCombo = partesComb[0];
			String descuentoStr = partesComb[1];
			double descuento = Double.parseDouble(descuentoStr.replace('%', '\u0000')) / 100;
			String[] itemsCombo = Arrays.copyOfRange(partesComb, 2, partesComb.length);
			Combo combo = new Combo(nombreCombo, descuento);
			for (String item: itemsCombo)
			{
				ProductoMenu productoMenu = mapaMenu.get(item);
				combo.agregarItemACombo(productoMenu);
			}
			combos.add(combo);
			lineaComb = brComb.readLine();
		}
		brComb.close();
	}
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		if (pedidoEnCurso == null)
		{
			pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
			System.out.println("\n Pedido inicializado. \n");
		}
		else
		{
			System.out.println("\n Ya hay un pedido en curso. Por favor cerrarlo para continuar. \n");
		}
	}
	public void agregarItemAPedidoEnCurso(IProducto prod)
	{
		pedidoEnCurso.agregarProducto(prod);
	}
	public String[] mostrarMenu()
	{
		String combosStr = "____________________________________________ \n";
		int numCombo = 1;
		for (Combo combo: combos)
		{
			String nombreCombo = String.valueOf(numCombo)+". "+combo.getNombre();
			String precioCombo = String.valueOf(combo.getPrecio());
			String elementosCombo = "";
			for (IProducto elemento:combo.getitemsCombo())
			{
				elementosCombo = elementosCombo +" "+ elemento.getNombre();
			}
			combosStr = combosStr + nombreCombo + "\t" + precioCombo + "\n" + "contiene: " + elementosCombo + "\n";
			numCombo++;
		}
		combosStr = combosStr + "____________________________________________ \n";
		String menuStr = "____________________________________________ \n";
		for (ProductoMenu prod: menuBase)
		{
			String nombreProd = prod.getNombre();
			String precioProd = String.valueOf(prod.getPrecio());
			menuStr = menuStr + nombreProd + "\t" + precioProd + "\n";
		}
		menuStr = menuStr + "____________________________________________ \n";
		String[] stringFinal = {combosStr, menuStr};
		return stringFinal;
		
	}
	public Combo getComboEspecifico(int Num)
	{
		Combo comboDado = combos.get(Num-1);
		return comboDado;
	}
	public ProductoMenu getProdEspecifico(int num)
	{
		ProductoMenu prod = menuBase.get(num-1);
		return prod;
	}
	public List<Ingrediente> anadirIngrediente(int numIng, List<Ingrediente> lista)
	{
		if (lista == null) {
			lista = new ArrayList<>();
		}
		Ingrediente ingredient = ingredientes.get(numIng-1);
		lista.add(ingredient);
		return lista;
	}
	public String mostrarIngredientes()
	{
		String menuStr = "____________________________________________ \n";
		for (Ingrediente prod: ingredientes)
		{
			String nombreProd = prod.getNombre();
			String precioProd = String.valueOf(prod.getCostoAdicional());
			menuStr = menuStr + nombreProd + "\t" + precioProd + "\n";
		}
		menuStr = menuStr + "____________________________________________ \n";
		return menuStr;
	}
	public ProductoAjustado ajustarProductoAnadir(ProductoMenu prodBase, List<Ingrediente> anadidos)
	{
		ProductoAjustado newProd = new ProductoAjustado(prodBase);
		for (Ingrediente ingredienteAnadido: anadidos) {
		newProd.agregarIngredienteAProducto(ingredienteAnadido);
		}
		return newProd;
	}
	public ProductoAjustado ajustarProductoEliminar(ProductoAjustado prodBase, List<Ingrediente> anadidos)
	{
		for (Ingrediente ingredienteAnadido: anadidos) {
		prodBase.eliminarIngredienteAProducto(ingredienteAnadido);
		}
		return prodBase;
	}
	public void cerrarYGuardarPedido() throws IOException
	{
		pedidos.put(pedidoEnCurso.getIdPedido(), pedidoEnCurso);
		pedidoEnCurso.guardarFactura();
		pedidoEnCurso = null;
	}
	public String consultarPedidoConId(int NumId)
	{
		Pedido pedido = pedidos.get(NumId);
		String textPedido = pedido.generarTextoFactura();
		return textPedido;
	}
}

