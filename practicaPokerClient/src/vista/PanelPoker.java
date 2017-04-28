package vista;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
/** Classe que prepara el panell on es mostrara tota la partida de Reproduir */
public class PanelPoker extends JPanel {

	private Image chips;
	private Image imatge_fons;
	private Image carta[][];
	private Image carta_mig[];
	private Image avatar[];
	
	private Point coord_imatge_fons;
	private Point coord_avatar[];
	private Point coord_aposta[];
	private Point coord_fitxes[];
	private Point coord_carta[];
	private Point coord_text_aposta[];
	private Point coord_carta_mig;
	private Point coord_pot;
	private Point coord_text_jugada;
	
	private String text_jugada[];
	private String nom_carta[];
	private String nom_carta_mig[];
	private String jug_aposta[];
	private String jug_fitxes[];
	private int pot;
	
	public PanelPoker() {
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		Color color_fons = new Color(23,27,30);
    	setBackground(color_fons);
    	nom_carta = new String[9];
    	nom_carta_mig = new String[5];
    	carta = 	new Image[9][2];
    	carta_mig = new Image[5];
    	avatar = 	new Image[9];
    	jug_aposta = new String[9];
    	jug_fitxes = new String[9];
    	inicialitzaDadesProva();
    	inicialitzaCoord();
	}
	
	/** Inicialitza les dades per primera vegada perque sigui possible posar el contingut del fitxer de Reproduir partida */
	public void inicialitzaDadesProva()
	{
		
		for (int i=0; i<9; i++)
		{
			nom_carta[i] = "00";
			jug_aposta[i] = null;
			
		}
		this.pot = 0;
		text_jugada = new String[]{null, null, null, null, null, null, null, null, null};
		nom_carta_mig = new String[]{null, null, null, null, null};
	}
	
	 public void paintComponent(Graphics g) {
	    	super.paintComponent(g); 
	    	carregaImatges();
	    	
	    	g.drawImage(imatge_fons, (int) coord_imatge_fons.getX(), (int) coord_imatge_fons.getY(), null);
	    	pintaCartesMig(g);
	    
	    	
	    	g.setColor(Color.white);
	    	g.drawString( pot+"" , (int) coord_pot.getX(), (int) coord_pot.getY()  );
			
	    	int max_aposta = buscaMaxAposta();
	    	
	    	/* si jug_fitxes = null vol dir que el jugador està aixecat i, per tant, no li cal pintar res
	    	** si jug_fitxes no es null pero la carta del jugador en questió si, vol dir que ha fet fold
	    	*/
	    	for (int i=0 ; i<9; i++){
	    		if (jug_fitxes[i] != null){
	    			g.drawImage(avatar[i], (int) coord_avatar[i].getX()	,(int) coord_avatar[i].getY(), 60 , 60 , null );
	    			g.drawString(jug_fitxes[i], (int) coord_fitxes[i].getX()  ,   (int) coord_fitxes[i].getY());
	    		}
	    		if (jug_aposta[i]!=null){
	    			g.drawString(jug_aposta[i] ,    (int) coord_text_aposta[i].getX(), (int) coord_text_aposta[i].getY());
	    		}
	    		
	    		if (carta[i][0] != null){
		    		g.drawImage(carta[i][0], (int) coord_carta[i].getX()  	  , (int) coord_carta[i].getY() , null);
		    		g.drawImage(carta[i][1], (int) coord_carta[i].getX() + 42 , (int) coord_carta[i].getY() , null);
		    		
	    		}
		    		//Pintarà les fitxes que té apostades cada jugador
			    		pintaChips(g , i, max_aposta);
			    		pintaTextMoviment(g,i);
	    		
	    		
	    	}
	    } 
	 
	private void pintaTextMoviment(Graphics g, int i){
		/* Petit algorisme dissenyat per tal de que el text quedi més o menys centrat
		 * Es basa en sumar o restar a la coordenada x on anirà pintat el text la llargada 
		 * de la cadena a pintar multiplicada per un coeficient que depèn d'aquesta mateixa llargada
		 */
		if (text_jugada[i]!=null){
		int length = text_jugada[i].length();
		
		if (length < 8){
			length *=5;
		} else if (length < 15){
				length *= 1.7;
			}else if (length < 25 ){
				length *= 1.2;
			} else {
				length /= 1.3;
		}
		g.setColor(Color.red);
		coord_text_aposta[i].setLocation( coord_text_aposta[i].getX() + length ,coord_text_aposta[i].getY() );
    	g.drawString(text_jugada[i] ,    (int) coord_text_aposta[i].getX(), (int) coord_text_aposta[i].getY());
    	coord_text_aposta[i].setLocation( coord_text_aposta[i].getX() - length ,coord_text_aposta[i].getY() );
    	g.setColor(Color.white);
		}
	}
	 
	private void pintaCartesMig(Graphics g) {
		 int width = 49;
		 int height = 66;
		 
		 for (int i = 0 ; i<5 ; i++)
			 if (carta_mig[i] != null)
				 g.drawImage(carta_mig[i] , (int) coord_carta_mig.getX() + i * (width+5) ,(int) coord_carta_mig.getY() ,width, height, null);
		
	}

	private void pintaChips(Graphics g, int i, int max){
			
	    	if (jug_aposta[i] != null && jug_aposta[i] != "0")	{
	    		
    			int aposta = (Integer.parseInt(jug_aposta[i]));
    		
	    		if (aposta > 2){
	    			g.drawImage(chips, (int) coord_aposta[i].getX()+20, (int) coord_aposta[i].getY()+10 ,null);
	    		}
    			if (aposta >= max * 0.25){
    				g.drawImage(chips, (int) coord_aposta[i].getX()+50, (int) coord_aposta[i].getY()+10 ,null);
    			}
    			if (aposta >= max * 0.5){
    				g.drawImage(chips, (int) coord_aposta[i].getX()+80, (int) coord_aposta[i].getY()+10 ,null);
    			}
    			if (aposta >= max * 0.75){
    				g.drawImage(chips, (int) coord_aposta[i].getX()+40, (int) coord_aposta[i].getY()+30 ,null);
    			}
    			if (aposta >= max){
    				g.drawImage(chips, (int) coord_aposta[i].getX()+70, (int) coord_aposta[i].getY()+30 ,null);
    			}
    			
			}
	} 
	
	/** Carrega totes les imatges guardades localment perque estiguin llestes per mostrar per pantalla */
	public void carregaImatges(){
		imatge_fons = new ImageIcon("img/poker.png").getImage();
		chips = new ImageIcon("img/chips.png").getImage();
		
		/* Inicialitzem els elements que pintarem sempre i quan no siguin null
		* Si el nom_carta esta a null vol dir que el jugador ha fet fold o esta dret
		* Si la nom_carta_mig esta a null vol dir que encara no s'ha obert
		*/
		for (int i = 0; i<9; i++){
			if (nom_carta[i] == null){
				carta[i][0] = new ImageIcon("img/girada.jpg").getImage();
				carta[i][1] = new ImageIcon("img/girada.jpg").getImage();
				
			} else if (nom_carta[i].indexOf("00")>=0){
					// No hem de pintar cap carta
					
					carta[i][0] = null;
				} else{
					
					carta[i][0] = new ImageIcon("img/"+ nom_carta[i].charAt(0)+nom_carta[i].charAt(1) +".jpg").getImage();
					carta[i][1] = new ImageIcon("img/"+ nom_carta[i].charAt(3)+nom_carta[i].charAt(4) +".jpg").getImage();
			}
			
			//Subbucle per carregar les cartes del mig
			if (i<5){
				if (nom_carta_mig[i] == null){
					
					carta_mig[i] = new ImageIcon("img/girada.jpg").getImage();
					
				} else	 if (nom_carta_mig[i].indexOf("00")>=0){
					
							carta_mig[i] = null;
						} else {
							carta_mig[i] = new ImageIcon("img/"+ nom_carta_mig[i].charAt(0)+nom_carta_mig[i].charAt(1) +".jpg").getImage();
				}
			}	
			
			if (jug_fitxes[i] != null){
				avatar[i] = new ImageIcon("img/avatar"+i+".png").getImage();
			}else{
				avatar[i] = null;
			}
		}
		
	}
	
	/** Actualitza el dibuix amb totes les dades d'entrada perque es mostri la partida l'estat actual de la partida que estem mirant
	 * @param nom_car Array amb tots els noms de les cartes dels jugadors en aquell moment
	 * @param aposta Array amb totes les apostes de tots els jugadors en aquell moment
	 * @param fitxes Array amb totes les fitxes de tots els jugadors en aquell moment
	 * @param pot Integer amb el pot actual en aquell moment
	 * @param nom_car_mig Array amb tots els noms de les cartes que hi ha al mig en aquell moment
	 * @param text_jugad Array amb totes les accions com Fold o Bet que ha fet els jugadors en aquell moment
	 */
	public void actualitzaDibuix(String[] nom_car, String aposta[], String fitxes[], int pot, String[] nom_car_mig, String text_jugad[],int giraCartes, int primerMove){
		setTextJugada(text_jugad);
		if (pot!=0 || primerMove==1){
			this.pot = pot;
		}
		if (nom_car[0]==null&&nom_car[1]==null&&nom_car[2]==null&&nom_car[3]==null&&nom_car[4]==null&&nom_car[5]==null&&nom_car[6]==null&&nom_car[7]==null&&nom_car[8]==null){
		}else{
			setCarta(nom_car);
		}
		setAposta(aposta);
		setFitxes(fitxes);
		if (nom_car_mig[0]!=null || giraCartes==1){
			setCartaMig(nom_car_mig);
		}
		repaint();
	}
	
	
	private void setCartaMig(String[] nom_car_mig) {
		for (int i=0; i<5 ; i++){
			nom_carta_mig[i] = nom_car_mig[i];
		}
	}
	private void setTextJugada(String[] text_jug){
		for (int i=0; i<9;i++){
			text_jugada[i]= text_jug[i];
		}
	}
	private void setFitxes(String[] fitxes){
		for (int i = 0; i<9 ; i++){
			if (fitxes[i]!=null){
				jug_fitxes[i] = fitxes [i];
			}
		}
	}
	
	private void setAposta(String[] aposta){
		for (int i = 0; i<9 ; i++){
			jug_aposta[i] = aposta[i];
		}
	}
	
	private void setCarta(String[] nom_car){
		for (int i=0; i<9 ; i++){
			nom_carta[i] = nom_car[i];
		}
	}
	
	public String[] desapareixCartes(String[] nom_car){
		for (int i=0; i<9 ; i++){
			nom_car[i]="00";
		}
		return nom_car;
	}
	
	/** Prepara totes les coordenades on es mostrara totes les imatges del panell */
	private void inicialitzaCoord(){
		demanaMemoriaPunts();
		coord_text_jugada.setLocation(440,75);
		coord_imatge_fons.setLocation(115, 85);
		coord_carta_mig.setLocation(coord_imatge_fons.getX() + 255  , coord_imatge_fons.getY() + 170 );
		coord_pot.setLocation(coord_imatge_fons.getX() + 390  , coord_imatge_fons.getY() + 130);
		
		int x = (int) coord_imatge_fons.getX();
		int y = (int) coord_imatge_fons.getY();
		
		/*el jugador 0 serà el de dalt a l'esquerra*/
		coord_avatar[0].setLocation( x + 236 , y + 10);
		coord_avatar[1].setLocation( x + 495 , y + 12);
		coord_avatar[2].setLocation( x + 630 , y + 87);
		coord_avatar[3].setLocation( x + 671 , y + 200);
		coord_avatar[4].setLocation( x + 567 , y + 315);
		coord_avatar[5].setLocation( x + 366 , y + 333);
		coord_avatar[6].setLocation( x + 167 , y + 316);
		coord_avatar[7].setLocation( x + 65  , y + 200);
		coord_avatar[8].setLocation( x + 104 , y + 83);
		
		
		coord_aposta[0].setLocation(210 + x , 70 + y);
		coord_aposta[1].setLocation(440 + x , 70 + y);
		coord_aposta[2].setLocation(520 + x , 120+ y);
		coord_aposta[3].setLocation(550 + x , 180+ y);
		coord_aposta[4].setLocation(480 + x , 230+ y);
		coord_aposta[5].setLocation(380 + x , 250+ y);
		coord_aposta[6].setLocation(220 + x , 240+ y);
		coord_aposta[7].setLocation(130 + x , 190+ y);
		coord_aposta[8].setLocation(150 + x , 120+ y);
		
		coord_carta[0].setLocation(x + 140 , y - 35);
		coord_carta[1].setLocation(x + 570 , y - 35);
		coord_carta[2].setLocation(x + 700 , y + 80);
		coord_carta[3].setLocation(x + 740 , y + 205);
		coord_carta[4].setLocation(x + 642 , y + 370);
		coord_carta[5].setLocation(x + 445 , y + 387);
		coord_carta[6].setLocation(x + 242 , y + 370);
		coord_carta[7].setLocation(x + 57  , y + 312);
		coord_carta[8].setLocation(x + 10  , y + 80);
		
		coord_text_aposta[0].setLocation(coord_carta[0].getX() + 34 , coord_carta[0].getY() + 90);
		coord_text_aposta[1].setLocation(coord_carta[1].getX() + 34 , coord_carta[1].getY() + 90);
		coord_text_aposta[2].setLocation(coord_carta[2].getX() + 25 , coord_carta[2].getY() + 95);
		coord_text_aposta[3].setLocation(coord_carta[3].getX() - 47 , coord_carta[3].getY() + 96);
		coord_text_aposta[4].setLocation(coord_carta[4].getX() + 31 , coord_carta[4].getY() - 8);
		coord_text_aposta[5].setLocation(coord_carta[5].getX() + 30 , coord_carta[5].getY() - 10);
		coord_text_aposta[6].setLocation(coord_carta[6].getX() + 27 , coord_carta[6].getY() - 8);
		coord_text_aposta[7].setLocation(coord_carta[7].getX() + 31 , coord_carta[7].getY() - 11);
		coord_text_aposta[8].setLocation(coord_carta[8].getX() + 42 , coord_carta[8].getY() + 95);
		
		for (int i = 0 ; i<9 ; i++){
			coord_fitxes[i].setLocation( coord_text_aposta[i].getX() - 9, coord_text_aposta[i].getY() - 19);
		}
		 coord_fitxes[0].setLocation(coord_fitxes[0].getX() + 5 , coord_fitxes[0].getY());
		 coord_fitxes[5].setLocation(coord_fitxes[5].getX() - 3 , coord_fitxes[5].getY());
		 coord_fitxes[6].setLocation(coord_fitxes[6].getX() + 10, coord_fitxes[6].getY());
	}

	/** Demana les coordenades on ha d'anar cada imatge */
	private void demanaMemoriaPunts() {
		coord_text_jugada = new Point();
		coord_imatge_fons = new Point();
		coord_pot =   		new Point();
		coord_carta_mig = 	new Point();
		coord_avatar = 		new Point[9];
		coord_aposta = 		new Point[9];
		coord_carta  = 		new Point[9];
		coord_fitxes = 		new Point[9];
		coord_text_aposta = new Point[9];
		
		for (int i = 0; i<9 ; i++){
			coord_avatar[i] = new Point();
			coord_aposta[i] = new Point();
			coord_carta [i] = new Point();
			coord_fitxes[i] = new Point();
			coord_text_aposta[i] = new Point();
		}
	}

	/**
	 * Funcio que busca la proporcio de fitxes que s'ha de mostrar per el panell en cas de que un jugador aposti
	 * @return Retorna quantes piles de fitxes s'ha de mostrar pel jugador
	 */
	private int buscaMaxAposta() {
		int max=0;
		for (int i = 0; i<9 ; i++){
			if (jug_aposta[i]!=null){
				if ( Integer.parseInt(jug_aposta[i]) > max ){
					max = Integer.parseInt(jug_aposta[i]);
				}
			}
		}
		return max;
	}


}
 
	 
