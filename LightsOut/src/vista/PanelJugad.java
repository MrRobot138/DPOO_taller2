package vista;

import java.awt.*;
import javax.swing.*;

public class PanelJugad extends JPanel{
	
	/**
	 * 
	 * Atributos:
	 */
	private JTextField txtNumJugadas;
	private JTextField txtNomJugador;
	private String nombreJugador;
	public int jugadas;
	
	public InterfazLightsOut interfazLightsOut;

	public PanelJugad(InterfazLightsOut interfazLightsOut) {
		this.interfazLightsOut = interfazLightsOut;
		this.setVisible(true);
		
		nombreJugador = "";
		jugadas = 0;
		
		/**
		 * Construimos las dimensiones para almacenar el contenido
		 * del panel:
		 * 
		 * | Jugadas: | n jugadas | Jugador: | Nombre Jugador |
		 * 
		 */
		this.setLayout(new GridLayout(1, 4));
		this.setPreferredSize(new Dimension(0, 30));
		
		this.setBackground(Color.WHITE);
		
		JLabel lblJugadas = new JLabel();
		lblJugadas.setText("Jugadas:");
		lblJugadas.setForeground(Color.BLACK);
		add(lblJugadas);
		
		txtNumJugadas = new JTextField();
		txtNumJugadas.setEditable(false);
		txtNumJugadas.setForeground(Color.BLACK);
		add(txtNumJugadas);
		
		JLabel lblJugador = new JLabel();
		lblJugador.setText("Jugador:");
		lblJugador.setForeground(Color.BLACK);
		add(lblJugador);
		
		txtNomJugador = new JTextField();
		txtNomJugador.setEditable(false);
		txtNomJugador.setForeground(Color.BLACK);
		add(txtNomJugador);
	}
	
	public void setNombreJugador(String nom) {
		nombreJugador = nom;
		txtNomJugador.setText(nombreJugador);
	}
	
	public String getNombreJugador() {
		return nombreJugador;
	}
	
	public void setJugadas(int puntajeActual) {
		txtNumJugadas.setText(Integer.toString(puntajeActual));
	}
}
