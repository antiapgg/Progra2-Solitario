package solitarioReal1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;

import solitarioReal1.CartaFranc.palosFranc;
import solitarioReal1.Pila.PileType;
import solitarioReal1.Funciones;

/**
 * JuegoSolitarioClasico
 */
public class SolitarioClasico {
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
		ArrayList<Pila> pila;		//ARRAY DE LAS PILAS CON LAS QUE "JUGAMOS"
		ArrayList<Pila> finalPila;	//ARRAY CON LAS PILAS DE FIN DE JUEGO
		Pila drawPila;				//PILA DE CARTAS OCULTAS (MAZO)
		Pila getPila;				//PILA DE CARTAS VISIBLES
		Pila PilaF;
		ArrayList<Pila> allPiles;	//ARRAY CON TODAS LAS PILAS
		public final int pileNumber = 7;
		public Baraja baraja;
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public SolitarioClasico() {
			
		/*	if(finish() == false) {
				//JOptionPane.showMessageDialog(this, "¿Seguro que quiere salir del juego actual?");
				System.out.println("¿Salir del juego actual?");
			}*/
			resetCards();
		}
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		/**
		 * Resetea todas las pilas del juego y la baraja
		 * 		Recibe: nada
		 * 		Devuelve: nada
		 **/
		public void resetCards() {
			
			baraja = new Baraja("Fran");
			baraja.barajar();
			
			drawPila = new Pila(120);
			drawPila.setOffset(0);
			
			getPila = new Pila(180);
			getPila.setOffset(0);
			
			finalPila = new ArrayList<Pila>();
			pila = new ArrayList<Pila>();
			
			allPiles = new ArrayList<Pila>();
			allPiles.add(drawPila);
			allPiles.add(getPila);
		}
		
		/**
		 * Set Up del juego, lo inicia. Dibuja las pilas, crea 7 pilas con 1 carta en la primera, 2 en la segunda... 
		 * 	Y la última carta de cada pila la deja visible.
		 * 		Recibe: nada
		 *		Devuelve: nada 		 
		 **/
		public void setupGame() {
			
			// Genera Pilas
			drawPila.type = PileType.Draw;
			getPila.type = PileType.Get;
	
			for(int i = 1; i <= pileNumber; ++i) {
				Pila p = new Pila(120);
				
				// Añado i cartas a la pila
				for(int j = 1; j <= i; ++j) { 
					CartaFranc card = baraja.drawCard(); 	//Dibujo las cartas de la pila 
					p.addCarta(card);
					
					if(j!=i)
						card.hide();
					else 
						card.show();
				}			
				pila.add(p);
				allPiles.add(p);
			}
			
			for(palosFranc suit : palosFranc.values()) {
				Pila p = new Pila(100);
				p.setOffset(0);
				p.type = PileType.Final;
				finalPila.add(p);	
				allPiles.add(p);
			}
			
			while(baraja.getNumeroDeCartas() > 0) {
				CartaFranc card = baraja.drawCard();
				card.hide();
				drawPila.addCarta(card);
			}
		}
		
		/**
		 * Dibuja una carta de la pila de Cartas y la coloca en getPila
		 * 		Recibe: nada
		 * 		Devuelve: nada
		 **/
		public void drawCard() {
			
			if(!drawPila.cards.isEmpty()) {
				CartaFranc drew = drawPila.dibujaCarta();
				drew.isReversed = false;
				getPila.addCarta(drew);			
			}
		}
		
		/**
		 * Cuando clicamos en una pila normal, si la carta de arriba esta boca abajo le damos la vuelta 
		 * 		Recibe: una Pila
		 * 		Devuelve: nada
		 **/
		public void clickPile(Pila p) {
			
			if(!p.cards.isEmpty()) {
				CartaFranc c = p.cards.get(p.cards.size() - 1);
				
				if(c.isReversed) {
					c.isReversed = false;
				}
			}
		}
		
		/**
		 * Le da la vuelta a la pila (Get) y la situa de nuevo para dibujar
		 * 		Recibe: nada
		 * 		Devuelve: nada
		 **/
		public void turnGetPile() {
			
			if(!drawPila.cards.isEmpty()) return;
			
			while(!getPila.cards.isEmpty()) {
				CartaFranc c = getPila.dibujaCarta();
				c.isReversed = true;
				
				drawPila.addCarta(c);
			}
		}
	
		/**
		 * Comprueba que todas las cartas esten colocadas en la pila correcta
		 * 		Recibe: nada
		 * 		Devuelve: un booleano que dice si las cartas estan colocadas en la pila correctamente o no
		 **/
		public boolean checkWin() {
			
			for(Pila p : finalPila) {
				
				if(p.cards.size() != 13)
					return false;
			}
			return true;
		}
	
		/**
		 * Método que da todas las pilas.
		 * 		Recibe: nada.
		 * 		Devuelve: una lista con las pilas.
		 **/
		public ArrayList<Pila> getAllPilas(){
			
			return allPiles;
		}
		
		/**
		 * Método que dael número de pilas.
		 * 		Recibe: nada.
		 * 		Devuelve: una entero con el número de pilas.
		 **/
		public int getPileNumber() {
			
			return pileNumber;
		}
		
		/**
		 * Método que da una pila.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public ArrayList<Pila> getPilas(){
			
			return pila;
		}
		
		/**
		 * Método que da todas la pila final.
		 * 		Recibe: nada.
		 * 		Devuelve: una lista con la pila final.
		 **/
		public ArrayList<Pila> getFinalPila(){
			
			return finalPila;
		}
		
		/**
		 * Método que da el dibujo de la pila.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public Pila getDrawPila() {
			
			return drawPila;
		}
		
		/**
		 * Método que da la pila dada.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public Pila getgetPila() {
			
			return getPila;
		}
		
		/**
		 * Método que comprueba si el juego actual se ha finalizado.
		 * 		Recibe: nada.
		 * 		Devuelve: booleano  fin. Si es true se ha finalizado.
		 **/
		public boolean finish() {
			
			boolean fin = false;
			
			if(finalPila != null) {
				
				if(checkWin() == true) {
					fin = true;
				}
				else {
					fin = false;
				}
			}
			return fin;
		}

		
		/**
		 * @throws IOException 
		 * 
		 **/
		
		public void saveCla(BufferedWriter bw) throws IOException {
			
			String saveString = "";
			String res = "";
			String newLine = System.getProperty( "line.separator" );
			String lastReverse = "false";
			StringBuffer rel = new StringBuffer();
			
			String[] lines = saveString.split(newLine);	
				
				String mazoOc = drawPila.toString();
				String cardStrings[] = mazoOc.split("-");
				
				for(String c: cardStrings) {

					String parts[] = c.split("_");	
					System.out.println("carta: "+c + " / " +cardStrings.length);
					
					res = parts[0] + parts[1];
					parts[0] = null;
					parts[1] = null;
					bw.write(res + " ");
				}
				
				bw.write("* ");
				
				String mazoVis = getPila.toString();
				String cardStrings2[] = mazoVis.split("-");
				
				for(String c: cardStrings2) {

					String parts[] = c.split("_");	
					
					res = parts[0] + parts[1];
					parts[0] = null;
					parts[1] = null;
	
					bw.write(res + " ");
				}
				bw.write(newLine);
				
				//Guarda cada pila en una nueva linea.
				for(Pila p : pila)
					saveString += p.toString() + newLine;
				
				for(Pila p: finalPila)
					saveString += p.toString() + newLine;
				
				for(String pile : lines) {	
					
					String cardStrings3[] = pile.split("-");
					
					for(String c: cardStrings3) {
						
						String parts[] = c.split("_");	
						System.out.println("HEY"+parts.length);
							if(parts.length >= 2) {
							res = parts[0] + parts[1];
							parts[0] = null;
							parts[1] = null;
						}
		
						bw.write(res + " ");
					}
				}
		
		}
		
		
}
