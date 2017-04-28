package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import vista.FinestraProgresFitxes;

/** Classe responsable de fer la lectura del fitxer que rep de Server perque es mostri a la finestra de joc */
public class lecturaFitxer {
	
	private Move moviment;
	private Hand ma;
	private Partida partid;
	@SuppressWarnings("unused")
	private FinestraProgresFitxes fpfitxes;
	private HTMLGenerator htmlgenerator;
	private String url;
	
	@SuppressWarnings({ "unused", "resource" })
	/** Funcio que llegeix tot el fitxer i ho prepara perquè es mostri per FinestraJoc correctament 
	 * @param fpfitxes Punter que apunta a la finestra de progres de fitxes perquè pugui fer el grafic de progres de les fitxes
	 * @param url String que conté la URL on està guardat el fitxer que ha de llegir la funció (guardat en files per defecte)
	 **/
	public lecturaFitxer(FinestraProgresFitxes fpfitxes, String url){
		FileReader fr = null;
		try {
			this.fpfitxes = fpfitxes;
			this.htmlgenerator = new HTMLGenerator();
			//Primer trobem qui es el jugador del que mostrarem les fitxes als grafics
			fr = new FileReader(url);
			BufferedReader entrada1 = new BufferedReader(fr);
			String cadena1 = entrada1.readLine();
			while (!cadena1.startsWith("Dealt")){
				cadena1 = entrada1.readLine();
			}
			char[] nomjug1= new char[30];
			for(int i=9;cadena1.charAt(i)!='[';i++){
				nomjug1[i-9]=cadena1.charAt(i);
			}
			String nombrejug1 = new String();
			nombrejug1=String.copyValueOf(nomjug1);
			char fase =' ';
			int contMGPreflop=0;
			int contMGFlop=0;
			int contMGTurn=0;
			int contMGRiver=0;
			int contMGShowdown=0;
			int contFPreFlop=0;
			int contFFlop =0;
			int contFTurn=0;
			int contFRiver=0;
			int contFoldsTotals=0;
			//comencem a llegir el fitxer
			fr = new FileReader(url);
			BufferedReader entrada = new BufferedReader(fr);
			String cadena = entrada.readLine();
			cadena = entrada.readLine();
			int contmans=0;
			partid = new Partida ();
			ProgresFitxes progfitxes= fpfitxes.getgDibuix();
			while (cadena!=null) {
				char[] nom = new char[15];
				char[] nomcart = new char[5];
				String nomcarte = new String();
				String nombre = new String();
				String numfitxes = new String();
				String win = new String();
				int i;
				int contmoviments=0;
				
				contmans++;
					while (!cadena.startsWith("Seat")){
						cadena = entrada.readLine();
					}
					String[] noms= new String[9];
					String nomj= new String();
					ma = new Hand ();
					moviment = new Move();
					contmoviments++;
					int[] numerufitxes = new int[9];
					int potee=0;
					//guardo el numero de fitxes i els noms dels jugadors
					while (cadena.startsWith("Seat")){
						nom= new char[30];
						for(i=8;cadena.charAt(i)!='(';i++){
							nom[i-8]=cadena.charAt(i);
						}
						nombre=String.copyValueOf(nom);
						noms[cadena.charAt(5)-49]=nombre;
						for(;cadena.charAt(i)!='(';i++){
						}
						int c=0;
						i++;
						char[] numerofitxes= new char[6];
						for (;cadena.charAt(i)!=')';i++){
							if (cadena.charAt(i)==','){
							}else{
								numerofitxes[c]=cadena.charAt(i);
								c++;
							}
						}
						char[] numerfitxes= new char[c];
						for(int k=0;k!=c;k++){
							numerfitxes[k]=numerofitxes[k];
						}
						numfitxes=String.copyValueOf(numerfitxes);
						numerufitxes[cadena.charAt(5)-49]=Integer.parseInt(numfitxes);
						//actualitzo graficaProgresFitxes amb les fitxes del jugador 
						if (nombre.equals(nombrejug1)){
							fpfitxes.actualitzaDadesGrafic(Integer.parseInt(numfitxes));
						}
						//afageixo les fitxes del jugador al moviment
						moviment.addFitxes(numfitxes,cadena.charAt(5)-49);
						cadena = entrada.readLine();
					}
					for(int j=0;j<9;j++){
						if (noms[j]==null){
							noms[j]="-";
						}
					}
					//afageixo el moviment (ja ple) a la ma
					ma.addMove(moviment, contmoviments-1);
					ma.setNumeroMoviments(contmoviments);
					i=0;
					moviment = new Move();
					contmoviments++;
					//mirem quines son les ciegas i qui les posa
					nom= new char[30];
					while (!(cadena.charAt(i)=='p' && cadena.charAt(i+1)=='o' && cadena.charAt(i+2)=='s' && cadena.charAt(i+3)=='t')){
						nom[i]=cadena.charAt(i);
						i++;
					}
					nomj = String.copyValueOf(nom);
					for(;cadena.charAt(i)!='f';i++){
					}
					i=i+2;
					int c = 0;
					char[] numerofitxes= new char[6];
					for (;cadena.length()!=i && !(cadena.charAt(i)==',' && cadena.charAt(i+1)==' ');i++){
						if (cadena.charAt(i)==',' ){
						}else{
							numerofitxes[c]=cadena.charAt(i);
							c++;
						}
					}
					char[] numerfitxes= new char[c];
					for(int k=0;k!=c;k++){
						numerfitxes[k]=numerofitxes[k];
					}
					numfitxes=String.copyValueOf(numerfitxes);
					int j=0;
					for(;!noms[j].equals(nomj);j++){
					}
					moviment.addAposta(numfitxes, j);
					moviment.addFitxes(Integer.toString(numerufitxes[j] - Integer.parseInt(numfitxes)),j);
					potee= potee + Integer.parseInt(numfitxes);
					moviment.setPot(potee);
					cadena = entrada.readLine();
					if (!cadena.startsWith("The")){
						i=0;
						nom= new char[30];
						while (!(cadena.charAt(i)=='p' && cadena.charAt(i+1)=='o' && cadena.charAt(i+2)=='s' && cadena.charAt(i+3)=='t') && !(cadena.charAt(i)=='h' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='s')){
							nom[i]=cadena.charAt(i);
							i++;
						}
						nomj = String.copyValueOf(nom);
						if (!(cadena.charAt(i)=='h' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='s')){
						for(;cadena.charAt(i)!='f';i++){
						}
						i=i+2;
						c = 0;
						numerofitxes= new char[6];
						for(;cadena.length()!=i && !(cadena.charAt(i)==',' && cadena.charAt(i+1)==' ');i++){
							if (cadena.charAt(i)==','){
							}else{
								numerofitxes[c]=cadena.charAt(i);
								c++;
							}
						}
						 numerfitxes= new char[c];
						for(int k=0;k!=c;k++){
							numerfitxes[k]=numerofitxes[k];
						}
						numfitxes=String.copyValueOf(numerfitxes);
						j=0;
						for(;!noms[j].equals(nomj);j++){
						}
						moviment.addAposta(numfitxes, j);
						moviment.addFitxes(Integer.toString(numerufitxes[j] - Integer.parseInt(numfitxes)),j);
						potee= potee + Integer.parseInt(numfitxes);
						moviment.setPot(potee);
						ma.addMove(moviment, contmoviments-1);
						ma.setNumeroMoviments(contmoviments);
						}
						cadena = entrada.readLine();
					}
					cadena = entrada.readLine();
					while (cadena!=null && !cadena.endsWith("****")){
					//Si estem en el PREFLOP
					if (cadena.charAt(4)=='H'){
						fase='p';//per indicar que estem al PREFLOP
						cadena = entrada.readLine();
						i=9;
						nom= new char[30];
						while (cadena.charAt(i)!='['){
							nom[i-9]=cadena.charAt(i);
							i++;
						}
						nomj = String.copyValueOf(nom);
						i=i+1;
						c=0;
						nomcart= new char[6];
						while (cadena.charAt(i)!=']'){
							nomcart[c]=cadena.charAt(i);
							c++;
							i++;
						}
						nomcarte = String.copyValueOf(nomcart);
						j=0;
						for(;!noms[j].equals(nomj);j++){
						}
						moviment = new Move();
						contmoviments++;
						moviment.addCartes(nomcarte, j);
						ma.addMove(moviment, contmoviments-1);
						ma.setNumeroMoviments(contmoviments);
						cadena = entrada.readLine();
					}
					//Si estem en el FLOP
					if (cadena.charAt(4)=='F'){
						//afegim les cartes del mig
						fase='f';//per indicar que estem al FLOP
						char[] cartmig= new char[2];
						String cartesmig = new String();
						cartmig[0]=cadena.charAt(14);
						cartmig[1]=cadena.charAt(15);
						cartesmig=String.copyValueOf(cartmig);
						moviment = new Move();
						contmoviments++;
						moviment.addCartesMig(cartesmig,0);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(17);
						cartmig[1]=cadena.charAt(18);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,1);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(20);
						cartmig[1]=cadena.charAt(21);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,2);
						ma.addMove(moviment, contmoviments-1);
						ma.setNumeroMoviments(contmoviments);
						cadena = entrada.readLine();
					}
					//Si estem en el TURN
					if (cadena.charAt(4)=='T'){
						//afegim les cartes del mig 
						fase='t';//per indicar que estem al TURN
						char[] cartmig= new char[2];
						String cartesmig = new String();
						cartmig[0]=cadena.charAt(14);
						cartmig[1]=cadena.charAt(15);
						cartesmig=String.copyValueOf(cartmig);
						moviment = new Move();
						contmoviments++;
						moviment.addCartesMig(cartesmig,0);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(17);
						cartmig[1]=cadena.charAt(18);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,1);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(20);
						cartmig[1]=cadena.charAt(21);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,2);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(25);
						cartmig[1]=cadena.charAt(26);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,3);
						ma.addMove(moviment, contmoviments-1);
						ma.setNumeroMoviments(contmoviments);
						cadena = entrada.readLine();
					}
					//Si estem en el RIVER
					if (cadena.charAt(4)=='R'){
						//afegim les cartes del mig 
						fase='r';//per indicar que estem al RIVER
						char[] cartmig= new char[2];
						String cartesmig = new String();
						cartmig[0]=cadena.charAt(15);
						cartmig[1]=cadena.charAt(16);
						cartesmig=String.copyValueOf(cartmig);
						moviment = new Move();
						contmoviments++;
						moviment.addCartesMig(cartesmig,0);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(18);
						cartmig[1]=cadena.charAt(19);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,1);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(21);
						cartmig[1]=cadena.charAt(22);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,2);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(24);
						cartmig[1]=cadena.charAt(25);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,3);
						cartmig= new char[2];
						cartmig[0]=cadena.charAt(29);
						cartmig[1]=cadena.charAt(30);
						cartesmig=String.copyValueOf(cartmig);
						moviment.addCartesMig(cartesmig,4);
						ma.addMove(moviment, contmoviments-1);
						ma.setNumeroMoviments(contmoviments);
						cadena = entrada.readLine();
					}
					//Si estem en el SHOW DOWN
					if (cadena.charAt(4)=='S' && cadena.charAt(5)=='H'){
						fase='s';//per indicar que estem SHOW DOWN
						cadena = entrada.readLine();
					}
					//Si estem en el SUMMARY
					if (cadena.charAt(4)=='S' && cadena.charAt(5)=='U'){
						while (cadena!=null && !cadena.endsWith("****")){
							cadena = entrada.readLine();
						}
					}
					int contFolds=0;
					int contJugadors=0;
					while(cadena!=null && !cadena.startsWith("***")){
						i=0;
						if (!cadena.startsWith("Uncalled")){
						nom= new char[30];
						while (!(cadena.charAt(i)=='h' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='s') && !(cadena.charAt(i)=='f' && cadena.charAt(i+1)=='o' && cadena.charAt(i+2)=='l'&& cadena.charAt(i+3)=='d') && !(cadena.charAt(i)=='c' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='l'&& cadena.charAt(i+3)=='l') && !(cadena.charAt(i)=='c' && cadena.charAt(i+1)=='h' && cadena.charAt(i+2)=='e'&& cadena.charAt(i+3)=='c') && !(cadena.charAt(i)=='m' && cadena.charAt(i+1)=='u' && cadena.charAt(i+2)=='c'&& cadena.charAt(i+3)=='k') && !(cadena.charAt(i)=='w' && cadena.charAt(i+1)=='i' && cadena.charAt(i+2)=='n'&& cadena.charAt(i+3)=='s') && !(cadena.charAt(i)=='r' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='i'&& cadena.charAt(i+3)=='s') && !(cadena.charAt(i)=='s' && cadena.charAt(i+1)=='h' && cadena.charAt(i+2)=='o'&& cadena.charAt(i+3)=='w') && !(cadena.charAt(i)=='b' && cadena.charAt(i+1)=='e' && cadena.charAt(i+2)=='t' && cadena.charAt(i+3)=='s') && !(cadena.charAt(i)==':') && !(cadena.charAt(i)=='i' && cadena.charAt(i+1)=='s' && cadena.charAt(i+2)==' ' && cadena.charAt(i+3)=='f' && cadena.charAt(i+4)=='e') && !(cadena.charAt(i)=='t' && cadena.charAt(i+1)=='i' && cadena.charAt(i+2)=='e')){
							nom[i]=cadena.charAt(i);
							i++;
						}
						if (cadena.charAt(i)==':'){
						}else{
							nomj = String.copyValueOf(nom);
							contJugadors++;
							j=0;
							for(;!(noms[j].equals(nomj));j++){
							}
							
						}
						}
						//quan jugador fa fold
						if (cadena.charAt(i)=='f'){
								//Generem el gràfic HTML per aquesta fase (folds)
								contFoldsTotals++;
								if (fase=='p'){
									contFPreFlop++;
									
								}
								if (fase=='f'){
									contFFlop++;
								}
								if (fase=='t'){
									contFTurn++;
								}
								if (fase=='r'){
									contFRiver++;
								}
						
							moviment = new Move();
							contmoviments++;
							moviment.addTextJugada("FOLD", j);
							ma.addMove(moviment, contmoviments-1);
							ma.setNumeroMoviments(contmoviments);
						}
						//quan jugador ensenya seves cartes
						if (cadena.charAt(i)=='s' && cadena.charAt(i+1)=='h'){
							c = 0;
							i=i+7;
							if (cadena.charAt(i-1)=='['){
								nomcart= new char[6];
								for(;cadena.charAt(i)!=']';i++){
									nomcart[c]=cadena.charAt(i);
									c++;
								}
								nomcarte=String.copyValueOf(nomcart);
								moviment = new Move();
								contmoviments++;
								moviment.addCartes(nomcarte, j);
								ma.addMove(moviment, contmoviments-1);
								ma.setNumeroMoviments(contmoviments);
							}
							i=0;
						}
						//quan jugador fa call o bet
						if ((cadena.charAt(i)=='c' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='l' && cadena.charAt(i+3)=='l') || (cadena.charAt(i)=='b' && cadena.charAt(i+1)=='e' && cadena.charAt(i+2)=='t' && cadena.charAt(i+3)=='s') || cadena.charAt(i)=='r' && cadena.charAt(i+1)=='a' && cadena.charAt(i+2)=='i'){
							if (cadena.charAt(i)=='c' && cadena.charAt(i+1)=='a'){
								i=i+6;
							}
							System.out.println(i);
							if (cadena.charAt(i)=='b' && cadena.charAt(i+1)=='e'){
								i=i+5;
							}
							if(cadena.charAt(i)=='r'){
								i=i+10;
							}
							c = 0;
							numerofitxes= new char[6];
							for(;cadena.length()!=i && !(cadena.charAt(i)==',' && cadena.charAt(i+1)==' ');i++){
								if (cadena.charAt(i)==','){
								}else{
									numerofitxes[c]=cadena.charAt(i);
									c++;
								}
							}
							numerfitxes= new char[c];
							for(int k=0;k!=c;k++){
								numerfitxes[k]=numerofitxes[k];
							}
							numfitxes=String.copyValueOf(numerfitxes);
							moviment = new Move();
							contmoviments++;
							moviment.addAposta(numfitxes, j);
							moviment.addFitxes(Integer.toString(numerufitxes[j] - Integer.parseInt(numfitxes)),j);
							potee= potee + Integer.parseInt(numfitxes);
							moviment.setPot(potee);
							ma.addMove(moviment, contmoviments-1);
							ma.setNumeroMoviments(contmoviments);
							i=0;
						}
						//quan jugador fa check
						if (cadena.charAt(i)=='c' && cadena.charAt(i+1)=='h' && cadena.charAt(i+2)=='e' && cadena.charAt(i+3)=='c'){
							moviment = new Move();
							contmoviments++;
							moviment.addTextJugada("CHECK", j);
							ma.addMove(moviment, contmoviments-1);
							ma.setNumeroMoviments(contmoviments);
						}
						//quan jugador wins the pot
						if (cadena.charAt(i)=='w' && cadena.charAt(i+1)=='i' && cadena.charAt(i+2)=='n' && cadena.charAt(i+3)=='s'){
							//actualitzem a quina fase (turn,river..) s'ha guanyat la ma per l'HTML
							if (fase=='p'){
								contMGPreflop++;
								htmlgenerator.actualitzaMGPreFlop();
							}
							if (fase=='f'){
								contMGFlop++;
								htmlgenerator.actualitzaMGFlop();
							}
							if (fase=='t'){
								contMGTurn++;
								htmlgenerator.actualitzaMGTurn();
							}
							if (fase=='r'){
								contMGRiver++;
								htmlgenerator.actualitzaMGRiver();
							}
							if (fase=='s'){
								contMGShowdown++;
								htmlgenerator.actualitzaMGShowdown();
							}
							//valorem el cas que sigui side/main pot (la linea es mes llarga)
							if (cadena.charAt(i+9)=='s' || cadena.charAt(i+9)=='m'){
								i=i+19;
							}else{
								i=i+14;
							}
							c = 0;
							numerofitxes= new char[6];
							for(;cadena.charAt(i)!=')';i++){
								if (cadena.charAt(i)==','){
								}else{
									
									numerofitxes[c]=cadena.charAt(i);
									c++;
								}
							}
							numerfitxes= new char[c];
							for(int k=0;k!=c;k++){
								numerfitxes[k]=numerofitxes[k];
							}
							numfitxes=String.copyValueOf(numerfitxes);
							win="WINS ";
							moviment = new Move();
							contmoviments++;
							moviment.resetPot();
							moviment.addTextJugada(win.concat(numfitxes), j);
							ma.addMove(moviment, contmoviments-1);
							ma.setNumeroMoviments(contmoviments);
						}
						cadena = entrada.readLine();
					}
					
					}
					this.partid.addHand(ma, contmans -1);
				}	
			fpfitxes.ferNouDibuix();
			htmlgenerator.actualitzaFPreFlop(contFPreFlop,contFoldsTotals);
			htmlgenerator.actualitzaFFlop(contFFlop, contFoldsTotals);
			htmlgenerator.actualitzaFTurn(contFTurn, contFoldsTotals);
			htmlgenerator.actualitzaFRiver(contFRiver, contFoldsTotals);
			htmlgenerator.actualitzaTotalMans(contmans);
			htmlgenerator.generateHTML("./files/grafica.html");
					
				
		
			
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}
	}
	
	public Partida getPartida(){
		return this.partid;
	}
	
	public void obrirfitxerHTLM() {
		System.out.println(url);
		File file = new File("./files/grafica.html");
		try {
			htmlgenerator.openHTML(file.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


