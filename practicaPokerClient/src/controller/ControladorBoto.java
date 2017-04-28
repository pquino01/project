package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import network.MessageService;

import vista.DialogFitxers;
import vista.FinestraClient;
import vista.FinestraJoc;
import vista.FinestraPrincipal;
import vista.FinestraProgresFitxes;
import vista.PanelPoker;

import model.lecturaFitxer;

/** Controlador que detecta quan es polsen els botons de les finestres */
public class ControladorBoto implements ActionListener {
	
	/** Componentes privados*/
	private FinestraProgresFitxes ffitxes;
	private FinestraPrincipal fp;
	private FinestraJoc f;
	private FinestraClient fj;
	private PanelPoker contenidor;
	private lecturaFitxer lf;
	private MessageService mService;
	private int contmoviments =-1;
	private int contmans=0;
	private String[] llista;
	private String fitxerEnviar;
	private DialogFitxers dfitxers;
	
	
	public ControladorBoto(FinestraPrincipal fp, FinestraJoc f, FinestraClient fj, FinestraProgresFitxes ffitxes){
		this.fj = fj;
		this.f = f;
		this.fp = fp;
		this.ffitxes = ffitxes;
		int giraCartes=0;
	}
	

	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getActionCommand().equals("PREVIOUSH")){
			if (this.contmans!=0 && this.contmans!=-1){
				this.contmans--;
			}
			this.contmoviments=-1;
			contenidor.inicialitzaDadesProva();
		} else if (evt.getActionCommand().equals("PREVIOUSM")){
			if (this.contmoviments!=0 && this.contmoviments!=-1){
				this.contmoviments--;
			}
			
			if (contmoviments==0 || contmoviments==1){
				if (contmoviments==1){
					contenidor.actualitzaDibuix(contenidor.desapareixCartes(lf.getPartida().getHand(contmans).getMove(contmoviments).getCartes()),lf.getPartida().getHand(contmans).getMove(contmoviments).getAposta(),lf.getPartida().getHand(contmans).getMove(contmoviments).getFitxes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getPot(),lf.getPartida().getHand(contmans).getMove(contmoviments).getCartesMig(),lf.getPartida().getHand(contmans).getMove(contmoviments).getTextJugada(),1,0);
				}else{
					contenidor.actualitzaDibuix(contenidor.desapareixCartes(lf.getPartida().getHand(contmans).getMove(contmoviments).getCartes()),lf.getPartida().getHand(contmans).getMove(contmoviments).getAposta(),lf.getPartida().getHand(contmans).getMove(contmoviments).getFitxes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getPot(),lf.getPartida().getHand(contmans).getMove(contmoviments).getCartesMig(),lf.getPartida().getHand(contmans).getMove(contmoviments).getTextJugada(),1,1);
				}
			}
			if (contmoviments<lf.getPartida().getHand(contmans).quanEsMostrenCartesMig() && lf.getPartida().getHand(contmans).quanEsMostrenCartesMig()!=0){
				contenidor.actualitzaDibuix(lf.getPartida().getHand(contmans).getMove(contmoviments).getCartes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getAposta(),lf.getPartida().getHand(contmans).getMove(contmoviments).getFitxes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getPot(),lf.getPartida().getHand(contmans).getMove(contmoviments).getCartesMig(),lf.getPartida().getHand(contmans).getMove(contmoviments).getTextJugada(),1,0);
			}else{
				contenidor.actualitzaDibuix(lf.getPartida().getHand(contmans).getMove(contmoviments).getCartes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getAposta(),lf.getPartida().getHand(contmans).getMove(contmoviments).getFitxes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getPot(),lf.getPartida().getHand(contmans).getMove(contmoviments).getCartesMig(),lf.getPartida().getHand(contmans).getMove(contmoviments).getTextJugada(),0,0);
			}
			
		} else if (evt.getActionCommand().equals("NEXTM")) {
			if (this.contmoviments<(lf.getPartida().getHand(contmans).numeroMoviments()-1)){
				this.contmoviments++;
			}
			contenidor.actualitzaDibuix(lf.getPartida().getHand(contmans).getMove(contmoviments).getCartes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getAposta(),lf.getPartida().getHand(contmans).getMove(contmoviments).getFitxes(),lf.getPartida().getHand(contmans).getMove(contmoviments).getPot(),lf.getPartida().getHand(contmans).getMove(contmoviments).getCartesMig(),lf.getPartida().getHand(contmans).getMove(contmoviments).getTextJugada(),0,0);
			
		} else if (evt.getActionCommand().equals("NEXTH")){
			if (this.contmans<(lf.getPartida().numeroMans()-1)){
				this.contmans++;
			}
			this.contmoviments=-1;
			contenidor.inicialitzaDadesProva();
		} else if (evt.getActionCommand().equals("BACCEDIR")) {
			
			if (fj.getNickPanel(1).isEmpty()||fj.getPassPanel(1).isEmpty()) {
				this.popup("OMPLE TOTS ELS CAMPS");
			} else {
				this.mService = new MessageService(this);
				String aux = mService.sendMessage(fj.getNickPanel(1), fj.getPassPanel(1),0);
				if (aux.equals("OK")) {
					this.mService = new MessageService(this);
					aux = mService.sendMessage("a","1",2);
					if (aux.equals("OK")) {
						fp.convertirAClient();
						llista = mService.getListaFitxers();
						this.popup("S'HA ACCEDIT DE FORMA CORRECTE");
					}
					else {
						this.popup("NO S'HA POGUT ACCEDIR A LA LLISTA DE FITXERS. LOGIN CANCELAT");
					}
				} else {
					this.popup("NO S'HA POGUT ACCEDIR");
				}
			}
			
		} else if (evt.getActionCommand().equals("BSORTIR")) {
			
			fj.amagarClient();
			f.amagarFinestra();
			ffitxes.amagarFinestra();
			fp.tornarAGuest();
			
			
		} else if (evt.getActionCommand().equals("BNOSORTIR")) {
			
			fj.amagarClient();
			
		} else if (evt.getActionCommand().equals("BREGISTRAR")) {
			
			this.mService = new MessageService(this);
			if (fj.getNickPanel(0).isEmpty()||fj.getPassPanel(0).isEmpty()||fj.getPassCheckPanel().isEmpty()) {
				this.popup("OMPLE TOTS ELS CAMPS");
			} else if (!fj.getPassPanel(0).equals(fj.getPassCheckPanel())) {
				this.popup("PASSWORD EN TOTS DOS CAMPS HA DE SER LA MATEIXA");
			} else {
				String aux = mService.sendMessage(fj.getNickPanel(0), fj.getPassPanel(0),1);
				if (aux.equals("OK")) {
					this.popup("S'HA REGISTRAT DE FORMA CORRECTE");
				}
				else {
					this.popup("NO S'HA POGUT REGISTRAR");
				}
			} 
		} else if (evt.getActionCommand().equals("BSELECT")) {
			fitxerEnviar = dfitxers.getSelectedChoice();
			mService = new MessageService(this);
			String aux = mService.sendMessage(fitxerEnviar,"a",3);
			if (!aux.equals("KO")) {
				fp.mostrarBarraGrafics();
            	ffitxes.borrarGrafic();
            	this.resetMovimientos();
                File file = new File(aux);
               String faux ="";
			try {
				faux = file.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
                this.lf = new lecturaFitxer(ffitxes,faux);
                System.out.println(file.getName());
    			f.mostrarFinestraJoc();
			}
		}
		
	}
	
	/** Funcio que crida un JDialog que mostra si el registre o login s'ha fet correctament 
	 * @param missatge Es el missatge que volem mostrar pel JDialog
	 **/
	private void popup(String missatge){
		 JOptionPane.showMessageDialog(contenidor,
			    missatge,
			    "Alerta",
			    JOptionPane.WARNING_MESSAGE);
		
	}
	
	public void setContenidor (PanelPoker cont){
		contenidor = cont;
	}
	
	public lecturaFitxer establirNouFitxer() {
		return lf;
	}
	
	public void resetMovimientos() {
		contmoviments =-1;
		contmans=0;
	}
	
	public void setLlista(String[] llista) {
		this.llista = llista;
	}
	
	public String[] getLlista() {
		return llista;
	}

	public void setDialog(DialogFitxers dfitxers) {

		this.dfitxers = dfitxers;
		
	}
}

