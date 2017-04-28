/*
 * Programa Client del Projecte PAM
 * @author Simeo Santiveri Llinas
 * @author Albert Trias Torroglosa
 * @author Pablo Quiñoa Serra
 * @author Guillem Batalla Puignou
 */



package model;

import javax.swing.SwingUtilities;

import controller.ControladorBoto;
import controller.ControladorMenu;
import vista.FinestraClient;
import vista.FinestraJoc;
import vista.FinestraPrincipal;
import vista.FinestraProgresFitxes;
/** Rutina principal del programa */
public class RutinaTest {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(
				
			new Runnable() {
				public void run() {
					
					FinestraPrincipal fprincipal = new FinestraPrincipal();
					FinestraClient fclient = new FinestraClient();
					FinestraProgresFitxes ffitxes = new FinestraProgresFitxes();
					FinestraJoc fjoc = new FinestraJoc();
					ControladorBoto cb = new ControladorBoto(fprincipal,fjoc,fclient,ffitxes);
					ControladorMenu cm = new ControladorMenu(fprincipal,fclient,ffitxes,fjoc,cb);
					fclient.registerController(cb);
					fprincipal.registerController(cm,cb);
					fjoc.setControladors(cb);
					fprincipal.setVisible(true);
			
				}
			}
		);

	}

}
