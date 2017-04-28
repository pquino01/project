package network;

import java.io.Serializable;

@SuppressWarnings("serial")

/** Classe que cont� l'array de fitxers que es transmet de Server a Client */
public class InformacioFitxers implements Serializable {
	
	/** Array de Strings que cont� nom de fitxers */
	private String[] llista;
	
	public InformacioFitxers() {
		
	}
	
	public String[] getLlista() {
		return llista;
	}
	
	public void setLlista(String[] llista) {
		this.llista = llista;
	}
}
