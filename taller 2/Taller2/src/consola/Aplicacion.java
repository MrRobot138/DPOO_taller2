package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import modelo.Restaurante;
import modelo.IProducto;
import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;
import modelo.Pedido;

public class Aplicacion {
	private Restaurante restaurante;

	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	private void ejecutarCargaRestaurante(Restaurante restaurante)
	{
		try
		{
			restaurante.cargarInformacionRestaurante();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	public void ejecutarAplicacion()
	{
		Restaurante restaurante = new Restaurante();
		System.out.println("Restaurante el Corral\n");
		ejecutarCargaRestaurante(restaurante);
		
		String[] menuStr = restaurante.mostrarMenu();
		
		boolean continuar = true;
		while (continuar == true)
		{
			try
			{
				
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					System.out.println("MENU \n COMBOS \n"+ menuStr[0] + "\n PRODUCTOS \n" + menuStr[1]);
				else if (opcion_seleccionada == 2 && restaurante != null)
				{
					System.out.println("\n Iniciar nuevo pedido: ");
					String nombreCliente = input("\n Ingrese su nombre: ");
					String direccionCliente = input("\n Ingrese su direccion: ");
					restaurante.iniciarPedido(nombreCliente, direccionCliente);
				}
				else if (opcion_seleccionada == 3 && restaurante != null)
				{
					int comboProd = Integer.parseInt(input("\n Por favor el tipo de producto (1: combo, 2: producto invididual"));
					if (comboProd == 1) {
						int numCombo = Integer.parseInt(input("\n Indique el numero del combo(1-4) \n"));
						restaurante.agregarItemAPedidoEnCurso(restaurante.getComboEspecifico(numCombo));
					}
					else if (comboProd == 2){
						int numProd = Integer.parseInt(input("\n Indique el numero del producto(1-22) \n"));
						ProductoMenu prod = restaurante.getProdEspecifico(numProd);
						ProductoAjustado newProd = anadirIngrediente(prod, restaurante);
						eliminarIngrediente(newProd, restaurante);
					}
				}
				else if (opcion_seleccionada == 4 && restaurante != null)
				{
					try {
						restaurante.cerrarYGuardarPedido();
						System.out.println("\n Se ha guardado la factura en el folder llamado facturas. \n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if (opcion_seleccionada == 5 && restaurante != null)
				{
					int idPedido = Integer.parseInt(input("\n Ingrese el numero id del pedido: "));
					String pedido = restaurante.consultarPedidoConId(idPedido);
					System.out.println(pedido);
				}
				else if (opcion_seleccionada == 6 && restaurante != null)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
			} catch (NumberFormatException e){
			System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
		}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public ProductoAjustado anadirIngrediente(ProductoMenu prodBase, Restaurante restaurante)
	{
		String anadir = input("\n desea anadir un ingrediente al producto? (1 = si, 2 = no)\n");
		List<Ingrediente> anadidos = new ArrayList<>();
		while (anadir.equals("1")){
			System.out.println(restaurante.mostrarIngredientes());
			int numProd = Integer.parseInt(input("\n Indique el numero del ingrediente(1-22) \n"));
			anadidos = restaurante.anadirIngrediente(numProd, anadidos);
			anadir = input("\n desea anadir otro ingrediente al producto? (1 = si, 2 = no)\n");
		}
		ProductoAjustado newProd = restaurante.ajustarProductoAnadir(prodBase, anadidos);
		return newProd;
	}
	public void eliminarIngrediente(ProductoAjustado prodBase, Restaurante restaurante)
	{
		String anadir = input("\n desea eliminar un ingrediente del producto? (1 = si, 2 = no)\n");
		List<Ingrediente> eliminados = new ArrayList<>();
		while (anadir.equals("1")) {
			System.out.println(restaurante.mostrarIngredientes());
			int numProd = Integer.parseInt(input("\n Indique el numero del ingrediente(1-22) \n"));
			eliminados = restaurante.anadirIngrediente(numProd, eliminados);
			anadir = input("\n desea eliminar otro ingrediente del producto? (1 = si, 2 = no)\n");
		}
		IProducto newProd = restaurante.ajustarProductoEliminar(prodBase, eliminados);
		restaurante.agregarItemAPedidoEnCurso(newProd);
	}
	public void mostrarOpciones()
	{
		System.out.println("1. Mostrar Menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar elemento a pedido");
		System.out.println("4. Cerrar pedido y guardar factura");
		System.out.println("5. Consultar pedido basado en id");
		System.out.println("6. Salir de aplicacion");
	}
}
