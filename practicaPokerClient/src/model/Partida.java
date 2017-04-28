package model;

/** Classe que cont� tota la informaci� que hi ha en una partida */
public class Partida {
	private Hand hands[];
	private int contmans;
	
	public Partida (){
		hands = new Hand[200];
	}
	public void addHand(Hand ma, int i){
		this.hands[i]=ma;
		this.contmans++;
	}
	public Hand getHand(int i){
		return hands[i];
	}
	public int numeroMans(){
		return this.contmans;
	}
}
