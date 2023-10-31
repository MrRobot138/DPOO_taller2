package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class LabelVisualTop10 extends JPanel implements ListCellRenderer<Object> {
	
	public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
		
		String valuer = (String) value;
		
		String[] valores = valuer.split(",");
		Integer puesto = Integer.valueOf(valores[0]);
		String mensaje;
		
		if (puesto != 10 ) {
		
			mensaje = valores[0] +"   " + valores[1] + "       " + valores[2];
			
			setFont(new Font("Helvetica", Font.BOLD, 14));
			if (puesto == 1) {
				setForeground(new Color(120, 66, 18));
			}
			else if (puesto == 2) {
				setForeground(new Color(154, 125, 10 ));
			}
			else if (puesto == 3) {
				setForeground(new Color(14, 102, 85  ));
			}
			else {
				setForeground(new Color(84, 153, 199));
			}
		
		}
		else{
			mensaje = valores[0] +" " + valores[1] + "       " + valores[2];
			setForeground(new Color(84, 153, 199));
		}
		
		setToolTipText(mensaje);
		setBackground(Color.white);
		setOpaque(true);
		return this;
	}
}
		
