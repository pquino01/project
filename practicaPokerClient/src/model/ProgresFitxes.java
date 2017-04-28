package model;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/** Classe que conté tota la informació necessaria per crear un grafic a FinestraProgresFitxes */
public class ProgresFitxes extends JPanel{

	private ArrayList<Integer> alFitxes;
	private int maxDades, maximFitxes;
	private String coordX, coordY;
	
	public ProgresFitxes() {
		alFitxes = new ArrayList<Integer>();
		maxDades = -1;
		maximFitxes = 0;
		coordY = "0";
		coordX = "0";
		drawGrafic();
		
	}
	
	/** Funcio que actualitza el grafic i el rescala tenim en compte la nova dada d'entrada
	 * @param fitxes Integer que conté el nou valor de fitxes del jugador que seguim
	 */
	public void actualitzaGrafic(int fitxes) {
		alFitxes.add(fitxes);
		maxDades++;
		coordX = Integer.toString(maxDades);
		if (fitxes > maximFitxes) {
			maximFitxes = fitxes;
			coordY = Integer.toString(fitxes);
		}
		drawGrafic();
		
	}
	
	public void drawGrafic() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		int preX = -1;
		int preY = -1;
		
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, 450);
		g.drawString(coordY, 60, 140);
		g.drawString("0", 80, 460);
		g.drawLine(100, 450, 550, 450);
		g.drawString(coordX,500,465);
		g.setColor(Color.BLUE);
		if (maxDades > 0) {
			for (int i = 0; i <= maxDades; i++) {
				if (preX == -1 || preY == -1) {
					preX = 100;
					preY = (310-(alFitxes.get(0)*310/maximFitxes))+140; //CANVIAR AIXO
				} else {
					g.drawLine(preX,preY,((400/maxDades)*i+100),((310-(alFitxes.get(i)*310/maximFitxes))+140));
					preX = (400/maxDades)*i+100;
					preY = (310-(alFitxes.get(i)*310/maximFitxes))+140; //CANVIAR AIXO
				}
			}
		}
		
	}
	
	/** Borra tots els valors que té guardats i ho prepara per una nova gràfica de un nou fitxer */
	public void reseteixaGrafic() {
		alFitxes = new ArrayList<Integer>();
		maxDades = -1;
		maximFitxes = 0;
		coordY = "0";
		coordX = "0";
		drawGrafic();
	}

	
	
}
