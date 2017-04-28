package vista;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.ControladorBoto;

@SuppressWarnings("serial")
/** Classe que genera un JDialog amb la llista de fitxers descarregada de Server */
public class DialogFitxers extends JDialog {
	
	private JPanel jpOptions;
	private JButton jbChoose;
	private JComboBox<String> jcbFitxers;
	
	public DialogFitxers(String[] llista) {
		
		jbChoose = new JButton("Select");
		jcbFitxers = new JComboBox<String>(llista);
		jcbFitxers.setPreferredSize(new Dimension(200,30));
		
		jpOptions = new JPanel();
		jpOptions.add(jcbFitxers);
		jpOptions.add(jbChoose);
		
		getContentPane().add(jpOptions);
		setTitle("Llista Fitxers");
		pack();
		setVisible(true);
		
	}
	
	/** Implementa ControladorBoto als botons del JDialog */
	public void setController(ControladorBoto cb) {
		jbChoose.setActionCommand("BSELECT");
		jbChoose.addActionListener(cb);
	}
	
	public String getSelectedChoice() {
		return jcbFitxers.getSelectedItem().toString();
	}
	
}
