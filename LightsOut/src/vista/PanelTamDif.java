package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class PanelTamDif extends JPanel implements ActionListener{
	
	/**
	 * Atributos:
	 */
	private InterfazLightsOut interfazLightsOut;
	private int desorden = 10;
	
	public int tamanio = 4;
	
	/**
	 * Constructor
	 * @param interfazLightsOut
	 * @param panelTablero 
	 */
	public PanelTamDif(InterfazLightsOut interfazLightsOut) {
		this.interfazLightsOut = interfazLightsOut;
		
		this.setVisible(true);

		
		/**
		 * Panel Tama�o y Diferencia:
		 * Construimos las dimensiones para almacenar el contenido
		 * del panel:
		 * 
		 *  | Tama�o: m*m | Dificultad: | Opciones de dificultad |
		 */
		this.setPreferredSize(new Dimension(0, 30));
		
		/**
		 * Bordes del panel:
		 * 
		 * LineBorder borde = new LineBorder(Color.YELLOW);
		 * borde.getBorderInsets(interfazLightsOut);
		 * setBorder(borde);
		 * 
		 * Tambien podr�an usarse bordes para destacar las limitaciones del panel,
		 * pero no se ve casi nada.
		 */
		
		this.setBackground(Color.BLACK);
		
		/**
		 * Ojetos de texto gr�ficos asociados al panel:
		 */
		
		JLabel lblTamanio = new JLabel();
		lblTamanio.setText("Tamaño:");
		lblTamanio.setForeground(Color.WHITE);
		add(lblTamanio);
		
		/**
		 * Dropdown button para los tama�os del tablero:
		 */
		String[] tamaniosStrings = {"4x4", "5x5", "6x6"};
		
		JComboBox<?> btnTamanios = new JComboBox<Object>(tamaniosStrings);
		btnTamanios.setBounds(70, 50, 0, 20);
		btnTamanios.setFocusable(false);
		btnTamanios.setSelectedIndex(0);
		btnTamanios.addActionListener(this);
		add(btnTamanios);
		
		//PanelTablero pintar = new pintarTablero(tamanioTablero);

		JLabel lblDificultad = new JLabel();
		lblDificultad.setText("Dificultad:");
		lblDificultad.setForeground(Color.WHITE);
		add(lblDificultad);
		
		/**
		 * Grupo de botones que conforman la dificultad del juego.
		 */
		CheckboxGroup nivelesDificultad = new CheckboxGroup();
		
		Checkbox facil = new Checkbox("Facil", nivelesDificultad, true);
		facil.setForeground(Color.WHITE);
		facil.setFocusable(false);
		facil.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    desorden = 10;
                } 
            }
		});

		add(facil);
		
		Checkbox medio = new Checkbox("Medio", nivelesDificultad, false);
		medio.setForeground(Color.WHITE);
		medio.setFocusable(false);
		medio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    desorden = 20;
                } 
            }
		});
		add(medio);
		
		Checkbox dificil = new Checkbox("Dificil", nivelesDificultad, false);
		dificil.setForeground(Color.WHITE);
		dificil.setFocusable(false);
		dificil.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    desorden = 30;
                } 
            }
		});
		add(dificil);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        String seleccion = (String) comboBox.getSelectedItem();
        if (seleccion != null) {
            try {
                int tam = Integer.parseInt(seleccion.split("x")[0]);
                interfazLightsOut.actualizarTablero(tam, desorden);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
}
