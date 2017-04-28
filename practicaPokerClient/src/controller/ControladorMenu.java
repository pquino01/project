package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.lecturaFitxer;

import vista.DialogFitxers;
import vista.FinestraClient;
import vista.FinestraJoc;
import vista.FinestraPrincipal;
import vista.FinestraProgresFitxes;

/** Controlador que detecta quan es polsen els menus de les finestres de Client */
public class ControladorMenu implements ActionListener {

	private DialogFitxers dfitxers;
	@SuppressWarnings("unused")
	private FinestraPrincipal fprincipal;
	private FinestraClient fjugador;
	private FinestraProgresFitxes fpfitxes;
	@SuppressWarnings("unused")
	private FinestraJoc fjoc;
	private ControladorBoto cb;
	private lecturaFitxer lf;
	private String[] llista;
	
	public ControladorMenu(FinestraPrincipal fprincipal, FinestraClient fjugador, FinestraProgresFitxes fpfitxes, FinestraJoc fjoc, ControladorBoto cb) {
		
		this.fprincipal = fprincipal;
		this.fjugador = fjugador;
		this.fpfitxes = fpfitxes;
		this.fjoc = fjoc;
		this.cb = cb;
	
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getActionCommand() == "LOGIN") {
			
			System.out.println("S'ha polsat el menu Log In");
			fjugador.showCorrectPanel(1);
			
		} else if (ae.getActionCommand() == "LOGOUT") {
			
			System.out.println("S'ha polsat el menu Log Out");
			fjugador.showCorrectPanel(2);
			
		} else if (ae.getActionCommand() == "REGISTRARM") {
			
			System.out.println("S'ha polsat el menu Register");
			fjugador.showCorrectPanel(0);
			
		} else if (ae.getActionCommand() == "GRAFICAM") {
			
			System.out.println("S'ha polsat el menu Grafica");
			fpfitxes.mostrarFinestra();

		} else if (ae.getActionCommand() == "REPRODUIRM"){
			
			

			llista = cb.getLlista();
			System.out.println(llista[0]);
			dfitxers = new DialogFitxers(llista);
			dfitxers.setController(cb);
			cb.setDialog(dfitxers);
			System.out.println("S'ha polsat el menu Reproduir");
			
		} else if (ae.getActionCommand() == "HTMLM") {
            this.lf = cb.establirNouFitxer();
			lf.obrirfitxerHTLM();
			System.out.println("S'ha polsat el menu HTML");
		}
	}
	
	public void establirNouFitxer(lecturaFitxer lf) {
		this.lf = lf;
	}
	
}
