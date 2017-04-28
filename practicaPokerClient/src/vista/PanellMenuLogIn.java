package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControladorBoto;

@SuppressWarnings("serial")
/** Classe que conte el panell on estan les opcions de fer un login */
public class PanellMenuLogIn extends JPanel{

	private static final int XTextA = 300;
	private static final int YTextA = 20;
	// Elements grafics de la GUI definits com a atributs de la classe
	private JPanel jpNick;
	private JLabel jlNick;
	private JTextField jtfNick;
	private JPanel jpPass;
	private JLabel jlPass;
	private JPasswordField jpfPass;
	private JButton jbLogIn;
	private JPanel jpLogIn;
	
	public PanellMenuLogIn() {
		
		setLayout(new GridLayout(3,1));
		
		setNickPanel();
		this.add(jpNick);
		
		setPassPanel();
		this.add(jpPass);
		
		setLoginButton();
		this.add(jpLogIn);
			
	}
	
	private void setNickPanel() {
		
		jlNick = new JLabel("Nick");
		jlNick.setAlignmentX(FlowLayout.LEFT);
		jtfNick = new JTextField();
		jtfNick.setPreferredSize(new Dimension(XTextA,YTextA));
		jpNick = new JPanel();
		((FlowLayout)jpNick.getLayout()).setAlignment(FlowLayout.LEFT);
		jpNick.add(jlNick);
		jpNick.add(jtfNick);
		
	}
	
	private void setPassPanel() {

		jlPass = new JLabel("Password");
		jlPass.setAlignmentX(FlowLayout.LEFT);
		jpfPass = new JPasswordField();
		jpfPass.setPreferredSize(jtfNick.getPreferredSize());
		jpPass = new JPanel();
		((FlowLayout)jpPass.getLayout()).setAlignment(FlowLayout.LEFT);
		jpPass.add(jlPass);
		jpPass.add(jpfPass);
		
	}
	
	private void setLoginButton() {

		jbLogIn = new JButton("Log In");
		jpLogIn = new JPanel();
		((FlowLayout)jpLogIn.getLayout()).setAlignment(FlowLayout.CENTER);
		jpLogIn.add(jbLogIn);
		
	}

	/** Implementa el ControladorBoto a tots els botons del panell */
	public void registerControllerPMLI(ControladorBoto cb) {
	
		jbLogIn.addActionListener(cb);
		jbLogIn.setActionCommand("BACCEDIR");
		
	}
	
	public String getNick() {
		
		return jtfNick.getText();
		
	}
	
	public String getPass() {
		return new String(jpfPass.getPassword());
	}

}
