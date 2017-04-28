package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ControladorBoto;


@SuppressWarnings("serial")
/** Classe que crea la FinestraJoc on es veura la reproduccio de la partida */
public class FinestraJoc extends JFrame {
	
	private PanelPoker contenidor;
	
	private JPanel panell_inferior;
	
	private JButton previous_h;
	private JButton previous_m;
	private JButton next_h;
	private JButton next_m;

	
        
	public FinestraJoc() {
		
		panell_inferior = new JPanel();
		contenidor = new PanelPoker();
		
		previous_h = new JButton("<< Previous hand");
		previous_m = new JButton("< Previous move");
		next_m = new JButton("Next move >");
		next_h = new JButton("Next hand >>");
		
		panell_inferior.add(previous_h);
		panell_inferior.add(previous_m);
		panell_inferior.add(next_m);
		panell_inferior.add(next_h);
		
		
		getContentPane().add(contenidor,BorderLayout.CENTER);
		getContentPane().add(panell_inferior,BorderLayout.PAGE_END);
		
        setSize(1026,650);
        setResizable(false);
        setVisible(false);
        setTitle("Pràctica Poker");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	/** Implementa ControladorBoto a tots els botons de la finestra */
	public void setControladors(ControladorBoto cboto){

		cboto.setContenidor(this.contenidor);
		previous_h.addActionListener(cboto);
		previous_h.setActionCommand("PREVIOUSH");
		previous_m.addActionListener(cboto);
		previous_m.setActionCommand("PREVIOUSM");
		next_h.addActionListener(cboto);
		next_h.setActionCommand("NEXTH");
		next_m.addActionListener(cboto);
		next_m.setActionCommand("NEXTM");
		
	}
	
	public void mostrarFinestraJoc() {
		this.setVisible(true);
	}

	public void amagarFinestra() {
		
		this.setVisible(false);
		
	}
	
 }


