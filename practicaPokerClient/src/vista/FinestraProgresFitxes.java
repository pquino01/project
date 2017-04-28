package vista;



import java.awt.Color;

import javax.swing.JFrame;

import model.ProgresFitxes;


@SuppressWarnings("serial")
/** Classe que crea la finestra on es veura la grafica de progres de fitxes */
public class FinestraProgresFitxes extends JFrame {

	private ProgresFitxes gDibuix;
	
	public FinestraProgresFitxes() {
		
		gDibuix = new ProgresFitxes();
		getContentPane().add(gDibuix);
		gDibuix.drawGrafic();
		
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Estadistiques Fitxes");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,100);
		setSize(700,600);
		setVisible(false);
		
	}
	
	/**
	 * Funcio que introdueix una nova fitxa a la grafica i actualitza la grafica visualment
	 * @param y Integer que conte la nova fitxa a introduir a la grafica
	 */
	public void actualitzaDadesGrafic(int y) {
		gDibuix.actualitzaGrafic(y);
	}

	public void mostrarFinestra() {
		setVisible(true);
		
	}
	
	/**
	 * Funcio que borra tot el contingut de gDibuix per poder aixi crear una nova grafica en cas de seleccionar un altre fitxer
	 */
	public void borrarGrafic() {
		gDibuix.reseteixaGrafic();
	}
	
	public void amagarFinestra() {
		setVisible(false);
	}
	
	/**
	 * Funcio que permet fer el dibuix de la grafica des del principi, sense cap valor introduit encara
	 */
	public void ferNouDibuix() {
		gDibuix.drawGrafic();
	}
	
	public ProgresFitxes getgDibuix() {
		return gDibuix;
	}
	
}
