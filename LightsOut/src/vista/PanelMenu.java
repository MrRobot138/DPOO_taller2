package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelMenu extends JPanel implements ActionListener{
	
	/**
	 * Atributos:
	 */
	public InterfazLightsOut interfazLightsOut;
	
	/**
	 * Constantes
	 */
	
	public final static String NUEVO_JUEGO = "NUEVO JUEGO";
	public final static String REINICIAR = "REINICIAR";
	public final static String TOP_10 = "TOP-10";
	public final static String CAMBIAR_JUGADOR = "CAMBIAR JUGADOR";
	

	public PanelMenu(InterfazLightsOut interfazLightsOut) {
		
		this.interfazLightsOut = interfazLightsOut;
		
		setBorder(new EmptyBorder(130, 10, 130, 10));
        setLayout(new BorderLayout());
		this.setVisible(true);
		
		/**
		 * Construimos las dimensiones para almacenar el contenido
		 * del panel:
		 * 
		 * |   NUEVO   |
		 * -------------
		 * | REINICIAR |
		 * -------------
		 * |  TOP-10   |
		 * -------------
		 * | CAMB.JUG. |
		 * 
		 */
		this.setPreferredSize(new Dimension(180, 400));
		this.setBackground(Color.WHITE);
		
		JPanel pnlMenu = new JPanel(new GridLayout(5,1));
		pnlMenu.setPreferredSize(new Dimension(50,200));
		pnlMenu.setBackground(Color.WHITE);
		
		JLabel lblMenu = new JLabel("MENU", SwingConstants.CENTER);
		pnlMenu.add(lblMenu);
		
		JButton btnNuevoJuego = new JButton(NUEVO_JUEGO);
		btnNuevoJuego.setBackground(Color.BLACK);
		btnNuevoJuego.setForeground(Color.WHITE);
		btnNuevoJuego.addActionListener(this);
		btnNuevoJuego.setActionCommand(NUEVO_JUEGO);
		pnlMenu.add(btnNuevoJuego);
		
		JButton btnReiniciar = new JButton(REINICIAR);
		btnReiniciar.setBackground(Color.BLACK);
		btnReiniciar.setForeground(Color.WHITE);
		btnReiniciar.addActionListener(this);
		btnReiniciar.setActionCommand(REINICIAR);
		pnlMenu.add(btnReiniciar);
		
		JButton btnTop10 = new JButton(TOP_10);
		btnTop10.setBackground(Color.BLACK);
		btnTop10.setForeground(Color.WHITE);
		btnTop10.addActionListener(this);
		btnTop10.setActionCommand(TOP_10);
		pnlMenu.add(btnTop10);
		
		JButton btnCambiarJugador = new JButton(CAMBIAR_JUGADOR);
		btnCambiarJugador.setBackground(Color.BLACK);
		btnCambiarJugador.setForeground(Color.WHITE);
		btnCambiarJugador.addActionListener(this);
		btnCambiarJugador.setActionCommand(CAMBIAR_JUGADOR);
		pnlMenu.add(btnCambiarJugador);
		
        add(pnlMenu, SwingConstants.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		//Tablero tablero = new Tablero(4);
		
		if(comando.equals(NUEVO_JUEGO)) {
			// Como no habia infromaci�n sobre lo que nuevo juego hac�a, se puso por default
			// que este bot�n inicia un nuevo juego en 4x4 con desorden de 10, que es el facil.
			
			int tam = interfazLightsOut.tamanio;
			int dif = interfazLightsOut.dificultad;
			
			interfazLightsOut.actualizarTablero(tam, dif);
		}
		
		else if (comando.equals(REINICIAR)) {
			try {
				interfazLightsOut.reiniciar();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (comando.equals(TOP_10)) {
			interfazLightsOut.mostrarTop10();
		}
		
		else if (comando.equals(CAMBIAR_JUGADOR)) {
			String nombreJugador = JOptionPane.showInputDialog("Elige tu nombre como jugador:");
			interfazLightsOut.nombreJugador(nombreJugador);
			
			
		}
		
	}

}
