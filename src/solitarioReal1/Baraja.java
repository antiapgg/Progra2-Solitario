package solitarioReal1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import solitarioReal1.CartaFranc;
import solitarioReal1.CartaFranc.palosFranc;


public class Baraja {
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		List<CartaFranc> cartas;
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR													//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public Baraja(String tipo) {
			
			//Creamos la baraja
			cartas = new ArrayList<CartaFranc>();
			//Llamamos a SolitarioClasico
			solitarioClasico();
		}

	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/**
		 * Método que crea el solitario Clasico.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void solitarioClasico(){
			
			for(palosFranc palosColor : palosFranc.values()) {
				
				for(int value = 1; value <= 14; ++value) {
					
					if(value != 11) {
						cartas.add(new CartaFranc(value, palosColor));
					}
				}
			}
		}
		
		/**
		 * Método que baraja las cartas.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void barajar() {
			
			Random randIndex = new Random();
			int size = cartas.size();
			
			for(int shuffles = 1; shuffles <= 20; ++shuffles) {
				
				for (int i = 0; i < size; i++) 
					Collections.swap(cartas, i, randIndex.nextInt(size));
			}		
		}
		
		/**
		 * Método que devuelve el número de cartas que hay en la baraja.
		 * 		Recibe: nada
		 * 		Devuelve: un entero, que se corresponde con el número de cartas que hay en la baraja
		 **/
		public int getNumeroDeCartas() {
			
			return cartas.size();
		}
		
		/**
		 * Método que dibuja la carta desde la baraja. La baraja no puede estar vacía.
		 * 		Recibe: nada
		 * 		Devuelve: una carta
		 **/
		public CartaFranc drawCard() {
			
			CartaFranc c = cartas.get(0);
			cartas.remove(0);
	
			return c;
		}
		
		/**
		 *  Método que muestra lo que hay en la baraja en x posición
		 * 		Recibe: un entero con la posición en la que hay que buscar
		 * 		Devuelve: la carta que hay en esa posición
		 **/
		public CartaFranc get(int x){
			
			return cartas.get(x);
		}
}

