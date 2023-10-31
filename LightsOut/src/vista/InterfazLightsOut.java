package vista;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

import modelo.*;

/**
 * Interfaz de juego Lights Out!
 */

public class InterfazLightsOut extends JFrame
{
	// ********************************
	// Atributos
	// ********************************
	
	/**
	 * Instancia de las clases que se encuentran en el modelo.
	 */
	public RegistroTop10 registroTop10;
	public Tablero tablero;
	public Top10 top10;
	public int tamanio = 4;
	public int dificultad = 10;
	
	public PanelJugad panelJugad;
	private PanelMenu panelMenu;
	private PanelTablero panelTablero;
	private PanelTamDif panelTamDif;
	
	// ********************************
	// Constructor
	// ********************************
	
	/**
	 * Construye la ventana principal de la interfaz sobre la cual se hacen
	 * las operaciones bases para agregar elementos gr�ficos.
	 */
	public InterfazLightsOut() {
		
		tablero = new Tablero(6);
		top10 = new Top10();
		
		this.setTitle("Lights Out");
		this.setSize(598, 500);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelTablero = new PanelTablero(this);
	    this.add(panelTablero, BorderLayout.CENTER);

	    panelTamDif = new PanelTamDif(this);
	    this.add(panelTamDif, BorderLayout.NORTH);

	    panelMenu = new PanelMenu(this);
	    this.add(panelMenu, BorderLayout.EAST);

	    panelJugad = new PanelJugad(this);
	    this.add(panelJugad, BorderLayout.SOUTH);
	    
	    tablero.desordenar(10);
	}
	
	public Tablero crearTablero(int tam) {
		panelJugad.jugadas = 0;
	    return new Tablero(tam); // Crea una nueva instancia de Tablero con el tama�o deseado
	}
	
	public void terminarJuego() {
		int jugadas = panelJugad.jugadas;
		if (tablero.tableroIluminado()) {
			int puntaje = (tamanio*tamanio)-jugadas+1;
			
			JOptionPane.showMessageDialog(
					this, "Felicidades " + panelJugad.getNombreJugador() + " GANASTE!\n" +
							"Tu puntaje fue: " + String.valueOf(puntaje)
					);
			
			tablero = crearTablero(tamanio);
			
			if (top10.esTop10(puntaje)) {
				if (panelJugad.getNombreJugador().equals("")) {
					String nombreJugador = JOptionPane.showInputDialog("Por favor escribe tu nombre de usuario:");
					panelJugad.setNombreJugador(nombreJugador);
				}
				top10.agregarRegistro(panelJugad.getNombreJugador(), puntaje);
			}
		}
	}


	/**
	 * Actualiza 
	 * @param tam
	 * @param desorden
	 */
	public void actualizarTablero(int tam, int desorden) {
		
		tablero = crearTablero(tam);
		tamanio = tam;
		tablero.desordenar(desorden);
		panelJugad.setJugadas(0);
	    panelTablero.rows = tam; // Actualiza el n�mero de filas en PanelTablero
	    panelTablero.cols = tam; // Actualiza el n�mero de columnas en PanelTablero
	    panelTablero.revalidate(); // Redibuja el panel de tablero
	    panelTablero.repaint();
		
    }
	
    public void nuevoRegistro(String nom, int puntaje) {
		top10.agregarRegistro(nom, puntaje);
    }
    
    public void nombreJugador(String nombreJugador) {
    	panelJugad.setNombreJugador(nombreJugador);
    }
    
    public void reiniciar() throws IOException {
    	tablero.reiniciar();
    	terminarJuego();
    	panelJugad.setJugadas(0);
    }
    
    public void mostrarTop10() {
    	Collection<RegistroTop10> elTop10 = top10.darRegistros();
    	PanelTop10 panelTop10 = new PanelTop10(elTop10);
    }
    
	
	public static void main(String[] args) {
		InterfazLightsOut interfaz = new InterfazLightsOut();
		interfaz.setVisible(true);
		//PanelMenu panelMenu = new PanelMenu(interfaz);
	}

}
