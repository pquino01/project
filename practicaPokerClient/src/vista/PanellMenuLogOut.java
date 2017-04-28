package vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControladorBoto;

@SuppressWarnings("serial")
/** 
 * Classe que genera el panell des de on es fa el Logout de FinestraClient
 */
public class PanellMenuLogOut extends JPanel {
	
	private JButton jbSi;
	private JButton jbNo;
	private JPanel jpSortir;
	private JLabel jlSortida;
	private JPanel jpSortida;
	
	public PanellMenuLogOut() {
		
		setLayout(new GridLayout(3,1));
		
		setLabel();
		this.add(jpSortida);
		
		setButtons();
		this.add(jpSortir);
			
	}

	/** Implementa el ControladorBoto a tots els botons del panell */
	public void registerControllerPMLO(ControladorBoto cb) {
		
		jbSi.addActionListener(cb);
		jbSi.setActionCommand("BSORTIR");
		jbNo.addActionListener(cb);
		jbNo.setActionCommand("BNOSORTIR");
		
	}
	
	public void setLabel(){
		jlSortida = new JLabel("Segur que vols SORTIR de POKER LS?");
		jlSortida.setAlignmentX(FlowLayout.CENTER);
		jpSortida = new JPanel();
		((FlowLayout)jpSortida.getLayout()).setAlignment(FlowLayout.CENTER);
		jpSortida.add(jlSortida);
	}
	
	private void setButtons() {
		jbSi = new JButton("Si");
		jbNo = new JButton("No");
		jpSortir = new JPanel();
		((FlowLayout)jpSortida.getLayout()).setAlignment(FlowLayout.CENTER);
		jpSortir.add(jbSi);
		jpSortir.add(jbNo);
		
	}
}
