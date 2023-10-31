package vista;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class PanelTablero extends JPanel {

    public InterfazLightsOut interfazLightsOut;

    public static int rows = 4;
    public static int cols = 4;

    public static final int cellSize = 80; // Tama�o de las celdas (ancho y alto)
    public static final int canvasWidth = 400;
    public static final int canvasHeight = 400;

    public PanelTablero(InterfazLightsOut interfazLightsOut) {

        this.interfazLightsOut = interfazLightsOut;
        this.setVisible(true);
        this.setLayout(new GridLayout(rows, cols));
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.GRAY);

        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    int row = e.getY() / cellSize;
        	    int col = e.getX() / cellSize;
        	    
        	    interfazLightsOut.panelJugad.setJugadas(interfazLightsOut.panelJugad.jugadas);
        	    interfazLightsOut.panelJugad.jugadas ++;
        	    
        	    if (row >= 0 && row < rows && col >= 0 && col < cols) {
        	    	
        	        // Cambiar el estado de la celda seleccionada
        	    	interfazLightsOut.tablero.darTablero()[row][col] = !interfazLightsOut.tablero.darTablero()[row][col];

        	        // Cambiar el estado de las celdas adyacentes
        	        if (row > 0) {
        	        	interfazLightsOut.tablero.darTablero()[row - 1][col] = !interfazLightsOut.tablero.darTablero()[row - 1][col];
        	        }
        	        if (row < rows - 1) {
        	        	interfazLightsOut.tablero.darTablero()[row + 1][col] = !interfazLightsOut.tablero.darTablero()[row + 1][col];
        	        }
        	        if (col > 0) {
        	        	interfazLightsOut.tablero.darTablero()[row][col - 1] = !interfazLightsOut.tablero.darTablero()[row][col - 1];
        	        }
        	        if (col < cols - 1) {
        	        	interfazLightsOut.tablero.darTablero()[row][col + 1] = !interfazLightsOut.tablero.darTablero()[row][col + 1];
        	        }
        	        
        	        getParent().repaint(); 
        	        
        	        interfazLightsOut.terminarJuego();
        	    }
        	}
        });
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(1));

        int cellSize = 80;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                RoundRectangle2D.Double rectangle = new RoundRectangle2D.Double(x, y, cellSize, cellSize, 8, 8);

                if (interfazLightsOut.tablero.darTablero()[i][j]) {
                    // Cambiar el color de fondo seg�n el estado de la celda en el tablero
                    g2D.setColor(Color.YELLOW);
                } else {
                	//tablero.jugar(i, j);
                    g2D.setColor(Color.BLACK);
                }

                g2D.fill(rectangle);
                g2D.setColor(Color.BLACK);
                g2D.draw(rectangle);
            }
        }
    }
}
