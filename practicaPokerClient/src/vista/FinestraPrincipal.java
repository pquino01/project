package vista;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.ControladorBoto;
import controller.ControladorMenu;

@SuppressWarnings("serial")
/** Finestra principal des d'on podrem seleccionar totes les opcions disponibles de Client */
public class FinestraPrincipal extends JFrame {
		
		private JMenuItem jmiLogIn;
		private JMenuItem jmiRegister;
		private JMenuItem jmiLogOut;
		private JMenuItem jmiReproduir;
		private JMenuItem jmiGrafica;
		private JMenuItem jmiHTML;
		private JMenuBar jmbBarraMenu;
		private JMenu jmClient;
		private JMenu jmMenuClient;
		
		public FinestraPrincipal() {
			
			//Barra superior on colocarem el Menu
			jmbBarraMenu = new JMenuBar();
			jmClient = new JMenu("Client");
			jmMenuClient = new JMenu("MenuClient");
			jmiReproduir = new JMenuItem("Reproduir Partida");
			jmiGrafica = new JMenuItem("Mostrar Grafica Fitxes");
			jmiHTML = new JMenuItem("Mostrar Taula HTML");
			jmiRegister = new JMenuItem("Register");
			jmiLogIn = new JMenuItem("Log In");
			jmiLogOut = new JMenuItem("Log Out");
			
			try {
				setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img/imatge_poker.jpg")))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			jmClient.add(jmiRegister);
			jmClient.add(jmiLogIn);
			jmClient.add(jmiLogOut);
			jmMenuClient.add(jmiReproduir);
			jmMenuClient.add(jmiGrafica);
			jmMenuClient.add(jmiHTML);
			jmbBarraMenu.add(jmClient);
			jmbBarraMenu.add(jmMenuClient);
			setJMenuBar(jmbBarraMenu);
			
			jmiGrafica.setVisible(false);
			jmiHTML.setVisible(false);
			jmMenuClient.setVisible(false);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Partida Poker");
			setSize(800, 600);
			setResizable(false);
			setVisible(true);
			
		}
		
	/** Implementa ControladorBoto i ControladorMenu a tots els menus i botons d'aquesta finestra */
	public void registerController(ControladorMenu cm, ControladorBoto cb){
			
		jmiRegister.addActionListener(cm);
		jmiRegister.setActionCommand("REGISTRARM");
		jmiLogIn.addActionListener(cm);
		jmiLogIn.setActionCommand("LOGIN");
		jmiLogOut.addActionListener(cm);
		jmiLogOut.setActionCommand("LOGOUT");
		jmiReproduir.addActionListener(cm);
		jmiReproduir.setActionCommand("REPRODUIRM");
		jmiGrafica.addActionListener(cm);
		jmiGrafica.setActionCommand("GRAFICAM");
		jmiHTML.addActionListener(cm);
		jmiHTML.setActionCommand("HTMLM");

	}

	public void mostrarMenu() {
		jmMenuClient.setVisible(true);
		
	}
	
	@SuppressWarnings("unused")
	/** Genera el fons de pantalla que podem veure a la finestra principal 
	 * @param path Conte el path on podem trobar la imatge que volem carregar
	 * @return Retorna un ImageIcon de la imatge que volem carregar com a fons de la finestra
	 */
	private ImageIcon createImageIcon(String path) {
    	java.net.URL imgURL = getClass().getResource(path);
    	if (imgURL != null) {
    		return new ImageIcon(imgURL);
    	} else {
    		System.err.println("Couldn't find file: " + path);
    		return null;
    	}
    }
	
	public void mostrarBarraGrafics() {
		
		jmiGrafica.setVisible(true);
		jmiHTML.setVisible(true);
		
	}

	/** Funcio que amaga totes les opcions de Client que no s'haurien de veure si encara no s'ha fet un login */
	public void tornarAGuest() {

		jmMenuClient.setVisible(false);
		jmiGrafica.setVisible(false);
		jmiHTML.setVisible(false);
		
	}

	/** Funcio que mostra Reproduir partida, que només es pot accedir si s'ha fet un login correcte */
	public void convertirAClient() {

		jmMenuClient.setVisible(true);
		
	}
	
	
}


