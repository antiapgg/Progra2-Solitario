package solitarioReal1;

import java.util.ArrayList;

import solitarioReal1.PilaSaltos.PileType;

public class SolitarioSaltos {
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ArrayList<PilaSaltos> pila;			//ARRAY CON LAS PILAS DE "JUEGO"
		ArrayList<PilaSaltos> finalPila;	//ARRAY CON LAS PILAS DE FIN DE JUEGO
		PilaSaltos drawPila;				//PILA DE CARTAS OCULTAS (MAZO)
		PilaSaltos getPila;					//PILA DE CARTAS VISIBLES
		ArrayList<PilaSaltos> allPiles;		//ARRAY CON TODAS LAS PILAS
		public BarajaEsp baraja;
		String barajaIntr;
		String[] auxiliar;
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		public SolitarioSaltos(String[] auxBaraja) {		
			
			auxiliar = auxBaraja;
			resetCards();
		}
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		/**
		 * Resetea todas las pilas del juego y la baraja.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void resetCards() {
			
			baraja = new BarajaEsp();
			baraja.recibe(auxiliar);
			
			drawPila = new PilaSaltos(120);		
			drawPila.setOffset(0);
				
			getPila = new PilaSaltos(180);
			getPila.setOffset(0);
				
			finalPila = new ArrayList<PilaSaltos>();
			pila = new ArrayList<PilaSaltos>();
				
			allPiles = new ArrayList<PilaSaltos>();
			allPiles.add(drawPila);
			allPiles.add(getPila);
		}
		
		/**
		 * Set Up del juego, lo inicia. Dibuja las pilas.
		 * 		Recibe: nada
		 *		Devuelve: nada 		 
		 **/
		public void setupJump(int num) {
			
			//Generamos las pilas
			drawPila.type = PileType.Draw;
			getPila.type = PileType.Get;
			
			//Recorro el numero de pilas
			for(int i = 1; i <= num; ++i) {
				//Creo una pila de tamaño 120
				PilaSaltos p = new PilaSaltos(120);	
				p.setOffset(20);	
				pila.add(p);
				allPiles.add(p);
			}			
			
			while(baraja.getNumeroDeCartas() > 0) {
				CartaEsp card = baraja.drawCard();
				card.hide();
				drawPila.addCarta(card);
			}
		}
		
		/**
		 * Dibuja una carta de la pila de Cartas y la coloca en getPila.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void drawCard() {
			
			if(!drawPila.cards.isEmpty()) {
				System.out.println("DIBUJOO");
				CartaEsp drew = drawPila.dibujaCarta();
				drew.isReversed = false;
				getPila.addCarta(drew);			
			}
		}
		
		/**
		 * Le da la vuelta a la pila (Get) y la situa de nuevo para dibujar.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void turnGetPile() {
			
			if(!drawPila.cards.isEmpty()) return;
			
			while(!getPila.cards.isEmpty()) {
				CartaEsp c = getPila.dibujaCarta();
				c.isReversed = true;			
				drawPila.addCarta(c);
			}
		}
	
		/**
		 * Comprueba que todas las cartas esten colocadas en la pila correcta.
		 * 		Recibe: nada.
		 * 		Devuelve: un booleano que dice si las cartas estan colocadas en la pila correctamente o no.
		 **/
		public boolean checkWin() {
			
			for(PilaSaltos p : pila) {
				System.out.println("TAM PILAS: "+p.cards.size());
				
				if(p.cards.size() != 40)
					return false;
			//	else {
					
			//	}
			}
			return true;
		}
	
		/**
		 * Método que da todas las pilas.
		 * 		Recibe: nada.
		 * 		Devuelve: una lista con las pilas.
		 **/
		public ArrayList<PilaSaltos> getAllPilas(){
			
			return allPiles;
		}
		
		/**
		 * Método que dael número de pilas.
		 * 		Recibe: nada.
		 * 		Devuelve: una entero con el número de pilas.
		 **/
		public boolean fin() {
			
			boolean fin = false;
			int auxFin = 1;

			if(checkWin() == true) {
				fin = true;
			}
			Juego jugar = new Juego();
			
			if(drawPila.isEmpty() == true) {
				
				for(int i = 0; i < allPiles.size(); i++) {
					
					if(allPiles.get(i) == jugar.pilaSAL.get(i)) {
						auxFin += 1;
					}
				}
				
				if(auxFin == allPiles.size())
					fin =  true;		
				}
			return fin;
		}
		
		/**
		 * Método que da una pila.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public ArrayList<PilaSaltos> getPilas(){
			
			return pila;
		}
		
		public int tamPilas() {
			return pila.size();
		}
		
		public PilaSaltos getPila(int pos) {
			
			return pila.get(pos);
		}
		/**
		 * Método que da todas la pila final.
		 * 		Recibe: nada.
		 * 		Devuelve: una lista con la pila final.
		 **/
		public ArrayList<PilaSaltos> getFinalPila(){
			
			return finalPila;
		}
		
		/**
		 * Método que da el dibujo de la pila.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public PilaSaltos getDrawPila() {
			
			return drawPila;
		}
		
		/**
		 * Método que da la pila dada.
		 * 		Recibe: nada.
		 * 		Devuelve: una pila.
		 **/
		public PilaSaltos getgetPila() {
			
			return getPila;
		}
		
		/**
		 * 
		 **/
		
		public void anhadePila() {
			
			PilaSaltos p = new PilaSaltos(120);	
			p.setOffset(20);	
			pila.add(p);
			allPiles.add(p);
		}


}
