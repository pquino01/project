package model;


/** Classe que conté tota la informació que hi ha en un Move */
public class Move {
	private String text_jugada[];
	private String nom_carta[];
	private String nom_carta_mig[];
	private String jug_aposta[];
	private String jug_fitxes[];
	private int pot;
	
	public Move() {
		nom_carta = new String[9];
    	nom_carta_mig = new String[5];
    	jug_aposta = new String[9];
    	jug_fitxes = new String[9];
    	text_jugada = new String[9];
	}
	public void addTextJugada (String textjugad, int i){
		this.text_jugada[i]=textjugad;
	}
	public String[] getTextJugada (){
		return this.text_jugada;
	}
	public void addCartes (String nomcar, int i){
		this.nom_carta[i]=nomcar;
	}
	public String[] getCartes (){
		return this.nom_carta;
	}
	public void addCartesMig (String nomcarmig, int i){
		this.nom_carta_mig[i]=nomcarmig;
	}
	public String[] getCartesMig (){
		return this.nom_carta_mig;
	}
	public void addAposta (String aposta, int i){
		this.jug_aposta[i]=aposta;
	}
	public String[] getAposta (){
		return this.jug_aposta;
	}
	public void addFitxes (String fitxes, int i){
		this.jug_fitxes[i]=fitxes;
	}
	public String[] getFitxes (){
		return this.jug_fitxes;
	}
	public void setPot (int pote){
		this.pot= pote;
	}
	public void resetPot (){
		this.pot=0;
	}
	public int getPot (){
		return this.pot;
	}
	public int CartesMigBuides(String nom_carta_mig[]){
			if (nom_carta_mig[0]==null){
				return 1;
			}else{
				return 0;
			}
	}
	public int quartaCartaBuida(String nom_carta_mig[]){
		if (nom_carta_mig[3]==null){
			return 1;
		}else{
			return 0;
		}
	}
	public int quintaCartaBuida(String nom_carta_mig[]){
		if (nom_carta_mig[4]==null){
			return 1;
		}else{
			return 0;
		}
	}

	
}
