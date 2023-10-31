package vista;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import modelo.RegistroTop10;

public class PanelTop10 extends JFrame{
	private LabelVisualTop10 labelVisualTop10;
	
	public PanelTop10(Collection<RegistroTop10> elTop10) {
		//setLayout(null);
		
		this.setTitle("Top 10");
		this.setSize(new Dimension(500, 500));
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		ArrayList<String> listaIntegrantes = new ArrayList<String>();
		Integer pos = 1;
		String infoIntegrante = "#       Nombre       Puntaje";
		JLabel primeraLinea = new JLabel(infoIntegrante);
		primeraLinea.setOpaque(true);
		primeraLinea.setFont(new Font("Helvetica", Font.PLAIN, 14));
		primeraLinea.setForeground(Color.white);
		primeraLinea.setBackground(Color.BLACK);
		primeraLinea.setBounds(0, 0, 200, 25);
		add(primeraLinea);
		
		infoIntegrante = "";
		
		for (RegistroTop10 integrante : elTop10) {
			infoIntegrante += pos.toString() + "," + integrante.darNombre() + "," + integrante.darPuntos();
			listaIntegrantes.add(infoIntegrante);
			
			infoIntegrante = "";
			pos ++;
			
		}
		
		Object[] listaIntegrantes2 = listaIntegrantes.toArray();
		
		JList<?> listaFinal = new JList<Object>(listaIntegrantes2);
		listaFinal.setVisible(true);
		labelVisualTop10 = new LabelVisualTop10();
		listaFinal.setCellRenderer(labelVisualTop10);
		
		JScrollPane barraMovil = new JScrollPane(listaFinal);
		barraMovil.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		barraMovil.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		barraMovil.setFocusable(false);
		barraMovil.setSize(new Dimension(500, 500));
		barraMovil.setBounds(0,25,500,500);
		
		add(barraMovil, BorderLayout.CENTER);
		setBounds(300,300,165,213);
		
		
		
	}
	

}
