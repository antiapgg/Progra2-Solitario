package solitarioReal1;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import solitarioReal1.CartaEsp;

public class PilaSaltos extends JLayeredPane{

	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		CartaEsp base;
		ArrayList<CartaEsp> cards;
		int offset = 15;
		char suitFilter;
		int width;
		PilaSaltos parent;
		PileType type;
		
		enum PileType {Normal, Draw, Get};	//Enumeramos los tipos de pila
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		public PilaSaltos(int width) {
			
			cards = new ArrayList<CartaEsp>();
			this.width = width;
			String bas = ("100C");
			base = new CartaEsp(bas);
			add(base, 1, 0);
			
			type = PileType.Normal;
		}
		
		
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													 MÉTODOS		 										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Método que añade una nueva carta en la parte superior de la pila. Siempre se añade.
		 * 		Recibe: la carta a añadir.
		 * 		Devuelve: nada.
		 **/
		public void addCarta(CartaEsp c) {
			
			c.setLocation(0, offset * cards.size());
			cards.add(c);
	
			this.add(c, 1, 0);
			actualizaTam();
		}
		
		/**
		 * Método que elimina una carta de la pila. Siempre se elimina.
		 * 		Recibe: la carta a eliminar.
		 * 		Devuelve: nada.
		 **/
		public void eliminaCarta(CartaEsp c) {
			
			cards.remove(c);
			this.remove(c);
			
			actualizaTam();
		}
		
		/**
		 * Método que devuelve la primera carta de la pila, sin eliminarla.
		 * 		Recibe: nada.
		 * 		Devuelve: la carta de la cima de la pila.
		 **/
		public CartaEsp peekTopCarta() {
			
			return cards.get(cards.size() - 1);
		}
		
		/**
		 * Método que dibuja la carta de la pila. La pila no debe estar vacía.
		 * 		Recibe: nada.
		 * 		Devuelve: La carta a dibujar.
		 **/
		public CartaEsp dibujaCarta() {
			
			CartaEsp c = cards.get(0);
			eliminaCarta(c);
	
			return c;
		}
	
	/*
	 * Método que establece el ancho de la columna de pila.
	 * 		Recibe: el ancho.
	 * 		Devuelve: nada.
	 */
	public void setWidth(int width) {
		this.width = width;
		actualizaTam();		
	}
	
		/**
		 * Método que actualiza el tamaño de la pila basandose en el número de cartas que contiene.
		 * 		Recibe: nada.	
		 * 		Devuelve: nada.
		 **/
		public void actualizaTam() {
			
			int height = base.getSize().height;
			
			if(!cards.isEmpty()) {
				height += offset * (cards.size() - 1);
			}
	
			this.setPreferredSize(new Dimension(width, height));
			this.setSize(width, height);
		}
	
		
		/**
		 * Cambia la posición de la pila.
		 * 		Recibe: un entero con la distancia.
		 * 		Devuelve: nada.
		 **/
		public void setOffset(int offset) {
			this.offset = offset;
			actualizaTam();
		}
		
		/**
		 * Método que parte la pila en dos pilas. La mitad superior se queda en esta pila.
		 * 		Recibe: La primera carta donde se "rompe" la pila.
		 * 		Devuelve: nada.
		 **/
		public PilaSaltos divide(CartaEsp first) {
			
			PilaSaltos p = new PilaSaltos(100);
			
			for(int i = 0; i < cards.size(); ++i) {
				
				if(cards.get(i) == first) {
					
					for(; i < cards.size();) {
						p.addCarta(cards.get(i));
						eliminaCarta(cards.get(i));
					}
				}
			}
			
			p.parent = this;
			
			return p;
		}
	
	/*
	 * Merge the current pile with the given pile
	 * The given pile is placed on top
	 * @param {Pile} p The pile to merge with
	 */
	public void merge(PilaSaltos p) {
		for(CartaEsp c: p.cards)
			addCarta(c);
		
		actualizaTam();
	}
	
		/**
		 * Método que busca una carta en la pila basandose en el valor y el nombre del palo.
		 * 		Recibe: el número y el palo de la carta.
		 * 		Devuelve: la carta encontrada.
		 **/
		public CartaEsp buscaCarta(int numero, String palo) {
			
			for(CartaEsp c: cards) {
				String pal = Character.toString(c.getPalo());
				
				if(c.value == numero && pal.equals(palo))
					return c;
			}
			
			return null;
		}
	
		/**
		 * Método que comprueba si la pila esta vacía.
		 * 		Recibe: nada.
		 * 		Devuelve: un booleano de si la pila está vacía o no.
		 **/
		public boolean isEmpty() {
			
			return cards.size() == 0;
		}
		
		/**
		 *	Método que comprueba si el movimiento es válido basandose en las condiciones del Solitario Saltos.
		 *		Recibe: la pila.
		 *		Devuelve: un booleano de si es válido el movimiento o no.
		 **/
		public boolean movimientoValido(PilaSaltos p, PilaSaltos pPos) {
			
			boolean res = false;
			//No se puede añadir a si mismo
			if(this == p) return false;
			
			CartaEsp newCard = p.cards.get(0);

			switch(type) {
				case Normal:
					if(pPos.cards.isEmpty()) {
						return true;
					}
					else {
						CartaEsp topCard = pPos.cards.get(0);
						
						//Si tienen el mismo valor o palo se pueden apilar
						if(topCard.getNumero() == newCard.getNumero()){
							res = true;
						}
						else {
							if(topCard.getPalo() == newCard.getPalo()){
								res = true;
							}
							else {
								res = false;
							}
						}
					}
			}
			return res;
		}
	
		
	/*
	 * Método
	 * 
	 */
	public boolean isOptimizedDrawingEnabled() {
      return false;
	}

	
		/**
		 * Método que devuelve un string que contiene todas las cartas de la pila.
		 * 		Recibe: nada.
		 * 		Devuelve: un String con las cartas separadas por "-".
		 **/
		public String toString() {
			
			String result = "";
			
			result += base.saveAsString() + "-";
			
			for(CartaEsp c : cards) {
				result += c.saveAsString() + "-";
			}
			
			return result;
		}
		
	// Change baseline, so pile is aligned to top
	@Override
	public Component.BaselineResizeBehavior getBaselineResizeBehavior() {
	    return Component.BaselineResizeBehavior.CONSTANT_ASCENT;
	}

	@Override
	public int getBaseline(int width, int height) {
	    return 0;
	}

}
