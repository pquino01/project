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
/** Classe que crea el panell on es pot registrar un usuari des de FinestraClient */
public class PanellMenuRegister extends JPanel {

	private static final int XMax = 330;
	private static final int YMax = 210;
	private static final int XTextA = 150;
	private static final int YTextA = 20;
	// Elements grafics de la GUI definits com a atributs de la classe
	private JPanel jpNick;
	private JLabel jlNick;
	private JTextField jtfNick;
	private JPanel jpPass;
	private JLabel jlPass;
	private JPasswordField jpfPass;
	private JPanel jpPassConfirm;
	private JLabel jlPassConfirm;
	private JPasswordField jpfPassConfirm;
	private JButton jbRegister;
	private JPanel jpRegister;

	public PanellMenuRegister() {
		
		setLayout(new GridLayout(4,1));
		
		setNickPanel();
		this.add(jpNick);
		
		setPassPanel();
		this.add(jpPass);
		
		setPassConfirmPanel();
		this.add(jpPassConfirm);
		
		setRegisterButton();
		this.add(jpRegister);
		
		setVisible(false);
		setSize(XMax,YMax);
		
	}

	private void setRegisterButton() {

		jbRegister = new JButton("Register");
		jpRegister = new JPanel();
		((FlowLayout)jpRegister.getLayout()).setAlignment(FlowLayout.CENTER);
		jpRegister.add(jbRegister);
		
	}

	private void setPassConfirmPanel() {

		jlPassConfirm = new JLabel("Confirm Password");
		jlPassConfirm.setAlignmentX(FlowLayout.LEFT);
		jpfPassConfirm = new JPasswordField();
		jpfPassConfirm.setPreferredSize(jtfNick.getPreferredSize());
		jpPassConfirm = new JPanel();
		((FlowLayout)jpPassConfirm.getLayout()).setAlignment(FlowLayout.LEFT);
		jpPassConfirm.add(jlPassConfirm);
		jpPassConfirm.add(jpfPassConfirm);
		
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
	
	/** Funcio que implementa ControladorBoto als botons del panell */
	public void registerControllerPMR(ControladorBoto cb) {
		
		jbRegister.addActionListener(cb);
		jbRegister.setActionCommand("BREGISTRAR");
		
	}
	
	public String getNick() {
		return jtfNick.getText();
	}
	
	public String getPass() {
		return new String(jpfPass.getPassword());
	}
	
	public String getPassCheck() {
		return new String(jpfPassConfirm.getPassword());
	}
	
}
