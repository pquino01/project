package model;

/** Classe que conté tota la informació que hi ha en una ma */
public class Hand {
	private Move moviments[];
	private int contmoviments;
	
	public Hand(){
		moviments = new Move[100];
	}
	public void addMove(Move moviment, int i){
		this.moviments[i]=moviment;
		this.contmoviments++;
	}
	public Move getMove (int i){
		return moviments[i];
	}
	public void setNumeroMoviments(int cont){
		this.contmoviments= cont;
	}
	public int numeroMoviments(){
		return this.contmoviments;
	}
	public int quanEsMostrenCartesMig(){
		int i=0;
		for (;i<contmoviments;i++){
			if (moviments[i].CartesMigBuides(moviments[i].getCartesMig())==0){
				return i;
			}
		}
		return i;
	}
	public int quanEsMostra4Carta(){
		int i=0;
		for (;i<contmoviments;i++){
			if (moviments[i].quartaCartaBuida (moviments[i].getCartesMig())==0){
				return i;
			}
		}
		return i;
	}
	public int quanEsMostra5Carta(){
		int i=0;
		for (;i<contmoviments;i++){
			if (moviments[i].quintaCartaBuida(moviments[i].getCartesMig())==0){
				return i;
			}
		}
		return i;
	}
}
