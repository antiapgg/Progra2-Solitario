package solitarioReal1;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import solitarioReal1.CartaFranc.palosFranc;


public class Pila extends JLayeredPane {


	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		CartaFranc base;
		ArrayList<CartaFranc> cards;
		int offset = 15;
		palosFranc suitFilter;
		int width;
		Pila parent;
		PileType type;
		
		enum PileType {Normal, Draw, Get, Final};
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		public Pila(int width) {
			
			cards = new ArrayList<CartaFranc>();
			this.width = width;
			
			base = new CartaFranc(100, palosFranc.C);
			add(base, 1, 0);
			
			type = PileType.Normal;
		}
		
		
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													 MÉTODOS		 										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Método que añade una nueva carta en la parte superior de la pila. Siempre se añade.
		 * 		Recibe: la carta que se quiere añadir.
		 * 		Devuelve: nada.
		 **/
		public void addCarta(CartaFranc c) {
			
			c.setLocation(0, offset * cards.size());
			cards.add(c);
	
			this.add(c, 1, 0);
			actualizaTam();
		}
		
		/**
		 * Método que elimina una carta de la pila. Siempre se elimina.
		 * 		Recibe: la carta que se quiere eliminar.
		 * 		Devuelve: nada.
		 **/
		public void eliminaCarta(CartaFranc c) {
			
			cards.remove(c);
			this.remove(c);
			
			actualizaTam();
		}
		
		/**
		 * Método que devuelve la primera carta de la pila, sin eliminarla.
		 * 		Recibe: nada.
		 * 		Devuelve: la primera carta de la pila.
		 **/
		public CartaFranc peekTopCarta() {
			
			return cards.get(cards.size() - 1);
		}
		
		/**
		 * Método que dibuja la carta de la pila. La pila no debe estar vacía.
		 * 		Recibe: nada.
		 * 		Devuelve: una carta.
		 **/
		public CartaFranc dibujaCarta() {
			
			CartaFranc c = cards.get(0);
			eliminaCarta(c);
	
			return c;
		}
		public int tam() {
			return cards.size();
		}
	
	/*
	 * Tamaño pila
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
	
	
	/*
	 * Separacion Pila
	 */
	public void setOffset(int offset) {
		this.offset = offset;
		actualizaTam();
	}
	
		/**
		 * Método que parte la pila en dos pilas. La mitad superior se queda en esta pila.
		 * 		Recibe: la carta donde empieza la division de la pila.
		 * 		Devuelve: la pila.
		 **/
		public Pila divide(CartaFranc first) {
			
			Pila p = new Pila(100);
			
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
	 * Mezcla pilas
	 */
	public void merge(Pila p) {
		for(CartaFranc c: p.cards)
			addCarta(c);
		
		actualizaTam();
	}
	
		/**
		 * Método que busca una carta en la pila basandose en el valor y el nombre del palo.
		 * 		Recibe: el número de la carta, el palo de la carta.
		 * 		Devuelve: la carta encontrada.
		 **/
		public CartaFranc buscaCarta(int numero, String palo) {
			
			for(CartaFranc c: cards) {
				
				if(c.value == numero && c.palosColor.name().equals(palo))
					return c;
			}	
			return null;
		}
	
		/**
		 * Método que comprueba si la pila esta vacía.
		 *		Recibe: nada.
		 *		Devuelve: un booleano diciendo si esta vacia o no.
		 **/
		public boolean isEmpty() {
			
			return cards.size() == 0;
		}
		
		/**
		 *	Método que comprueba si el movimiento es válido basandose en las condiciones del Solitario Clásico.
		 *		Recibe: una pila.
		 *		Devuelve: un booleano diciendo si el movimiento es valido o no.
		 **/
		public boolean movimientoValido(Pila p) {
			
			//No se puede añadir a si mismo
			if(this == p) return false;
			
			CartaFranc newCard = p.cards.get(0);
			CartaFranc topCard;
			
			switch(type) {
			
				//Pilas de juego, en las que tenemos unas cartas, y podemos ordenar el resto intercalando colores y en orden descendente
				case Normal:
					
					//Si la pila no está vacía no puede recibir el Rey (si esta vacía solo se puede introducir el Rey)
					if(cards.isEmpty()) {
						
						if(newCard.value == 14) {
							return true;
						}
						else {
							return false;
						}
					}				
					topCard = cards.get(cards.size() - 1);
					
					if(topCard.isReversed) return false;
					
					//Color diferente y valores consecutivos descencientes
					if(topCard.palosColor.isRed != newCard.palosColor.isRed) {
						
					   if(topCard.value == newCard.value + 1 ||topCard.value ==  12 && newCard.value == 10) {
						   return true;				
					   }
					}
				break;
				
				//Pilas de fin (en las que ya ordenamos las cartas por palos y orden ascendente)
				case Final:
					
					//Combinar con una sola carta
					if(p.cards.size() > 1) return false;
					
					//Empieza con un as
					if(cards.isEmpty() && newCard.value == 1) {
						suitFilter = newCard.palosColor;
						return true;
					}
					
					//Tienen que ser del mismo color
					if(suitFilter != newCard.palosColor) return false;
					
					//Valores consecutivos ascendentes
					topCard = cards.get(cards.size() - 1);
					
					if(topCard.value == newCard.value - 1 ||
					   topCard.value ==  10 && newCard.value == 12) {
						return true;
					}
				break;
			}
			return false;
		}
	
	/*
	 * Método
	 */
	public boolean isOptimizedDrawingEnabled() {
        return false;
	}

	
		/**
		 * Método que devuelve un string que contiene todas las cartas de la pila.
		 * 		Recibe: nada.
		 * 		Devuelve: un String con las cartas de la pila separadas por "-".
		 **/
		public String toString() {
			
			String result = "";
			
			result += base.saveAsString() + "-";
			
			for(CartaFranc c : cards) {
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