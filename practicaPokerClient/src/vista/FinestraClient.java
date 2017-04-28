package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ControladorBoto;

@SuppressWarnings("serial")
/** Classe que genera la finestra on estan les opcions de Registrar, fer un Login o fer un Logout */
public class FinestraClient extends JFrame{
	
	private static final int XMax = 330;
	private static final int YMax = 210;
	
	private CardLayout cLayout = new CardLayout();
	private PanellMenuRegister pmrCard;
	private PanellMenuLogIn pmliCard;
	private PanellMenuLogOut pmloCard;
	private JPanel jpCard;
	
	public FinestraClient() {
		
		setCardLayout();
		getContentPane().add(jpCard,BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Registrar Client");
		setSize(XMax,YMax);
		setResizable(false);
		
	}

	private void setCardLayout() {

		jpCard = new JPanel();
		jpCard.setLayout(cLayout);
		pmrCard = new PanellMenuRegister();
		pmliCard = new PanellMenuLogIn();
		pmloCard = new PanellMenuLogOut();
		jpCard.add(pmrCard,"0");
		jpCard.add(pmliCard,"1");
		jpCard.add(pmloCard,"2");
		
	}
	
	/** Implementa el ControladorBoto a tots els botons dels panells que te la finestra */
	public void registerController(ControladorBoto cb) {
		
		pmrCard.registerControllerPMR(cb);
		pmliCard.registerControllerPMLI(cb);
		pmloCard.registerControllerPMLO(cb);
		
	}
	
	/**
	 * Funcio que mostra el panell que ens interessa veure en la FinestraClient
	 * @param a Indica quin panell volem veure: 0=Registrar, 1=Login, 2=Logout
	 */
	public void showCorrectPanel(int a) {
		
		switch(a) {
		
			case 0:
				cLayout.show(jpCard, "0");
				setTitle("Registrar Jugador");
				setVisible(true);
				break;
			case 1:
				cLayout.show(jpCard, "1");
				setTitle("Log In Jugador");
				setVisible(true);
				break;
			case 2:
				cLayout.show(jpCard, "2");
				setTitle("Log Out Jugador");
				setVisible(true);
			default:
				break;
		
		}
		
	}
	
	public String getNickPanel(int i) {
		switch(i) {
			case 0:
				return pmrCard.getNick();
			case 1:
				return pmliCard.getNick();
			default:
				return null;
		}
	}

	public String getPassPanel(int i) {
		switch(i) {
			case 0:
				return pmrCard.getPass();
			case 1:
				return pmliCard.getPass();
			default:
				return null;
		}
	}

	public String getPassCheckPanel() {
		return pmrCard.getPassCheck();
	}
	
	public void amagarClient(){
		setVisible(false);
	}

}
