package solitarioReal1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import solitarioReal1.CartaEsp;

public class BarajaEsp {


	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		private int next;
		List<CartaEsp> cartas;
		CartaEsp carta;
		String cartaAux;
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR													//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public BarajaEsp() {

			cartas = new ArrayList<CartaEsp>(40);
			next = 0;	
		}

	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/**
		 * Método que recibe todas las cartas y las añade a la baraja.
		 * 		Recibe: un array de String con las cartas (o posibles cartas).
		 **/
		public int recibe(String[] auxiliar) {
			
			int cont = 0;
			int res = 0;
			
			if (auxiliar.length == 40) {
				
				for(int i = 0; i < auxiliar.length; i++) {
					
					if(auxiliar[i].equals("#")) {
						JOptionPane.showMessageDialog(new JPanel(),"Entrada incorrecta. La carta "+auxiliar[i]+" no es válida en esta posición.");
						return 0;
					}
					else {
						cartaAux = auxiliar[i];
						CartaEsp carta = new CartaEsp(cartaAux);
						
						if(anhadir(carta) == false) {
							JOptionPane.showMessageDialog(new JPanel(),"Entrada incorrecta. La carta "+carta+" no es correcta, por lo que no se puede añadir.");
							return 0;
						}
						else {
							
							if(carta.sizze() != 2) {
								JOptionPane.showMessageDialog(new JPanel(),"Entrada incorrecta. La carta "+carta+" no tiene el número de caracteres correcto.");
								return 0;
							}
							else {
								addCarta(carta);
								cont++;
							}
						}
					}
				}
			}
			else {
				
				if((auxiliar.length == 1) && (auxiliar[0].equals("#"))){
					res = 1;
				}
				else {
					JOptionPane.showMessageDialog(new JPanel(),"Entrada incorrecta. La baraja no tiene el número de cartas correcto.");
					return 0;
				}
			}
			
			if(cont == 40) {				
				System.out.println("Cartas comprobadas");
				res = 1;
			}
			
			return res;
		}	

		/**
		 *  Método que dice si se puede anhadir una carta a la baraja
		 * 		Recibe: la carta a anhadir
		 * 		Devuelve: un boolean diciendo si se puede anhadir
		 **/
		public boolean anhadir(CartaEsp carta) {

			boolean anhadir = false;
			next = 0;
			
			bucle:
			if(next <= cartas.size()) {
				
				for(int i = 0; i < cartas.size()-next; i++) {
					
					if(carta.equals(cartas.get(i))) {
						anhadir = false;	
						System.out.println("Carta Repetida");
						break bucle;	//CARTA REPETIDA
					}
				}
				
				boolean cartaAux;
				cartaAux = comprueba(carta);
				
				if(cartaAux == false) {
					anhadir = false;
					System.out.println("Carta érronea");
					break bucle;
				}
				else {
					anhadir = true;
					next++;
				}
			}
			return anhadir;
		}
		
		/**
		 *  Método que anhade una carta a la baraja
		 * 		Recibe: la carta a anhadir
		 * 		Devuelve: nada
		 **/
		public void addCarta(CartaEsp carta) {
			
			cartas.add(carta);
		}
		
		/**
		 *  Método que muestra lo que hay en la baraja en x posición
		 * 		Recibe: un entero con la posición en la que hay que buscar
		 * 		Devuelve: la carta que hay en esa posición
		 **/
		public CartaEsp get(int x){
			
			return cartas.get(x);
		}
		
		/**
		 *  Método que devuelve el número de cartas que hay en la baraja
		 * 		Recibe: nada
		 * 		Devuelve: un entero, que se corresponde con el número de cartas que hay en la baraja
		 **/
		public int getNumeroDeCartas() {	
			
			return cartas.size();
		}
		
		/**
		 *  Método que comprueba si la carta es correcta
		 * 		Recibe: la carta para comprobar
		 * 		Devuelve: un boolean  indicando si es correcto o no
		 **/
		public boolean comprueba(CartaEsp carta) {
			
			char aux[] = {'A', '2', '3', '4', '5', '6', '7', 'S', 'C', 'R'};
			char aux2[] = {'B', 'C', 'E', 'O'};
			int erAux = 0;	//Auxiliar que nos ayuda a saber si hay errores en la entrada
			int erAux2 = 0;	//Auxiliar que nos ayuda a saber si hay errores en la entrada
			boolean comprueba = false;
			
			for(int j = 0; j < aux.length; j++) {		
				
				if(carta.getNumero() == aux[j]) {	//Si el 1º digito existe	
					
					for(int k = 0; k < aux2.length; k++) {
						
						if(carta.getPalo() == aux2[k]) {	//Si el 2º digito existe
								comprueba = true;
						}
						else {									
							erAux2++;
							
							if(erAux2 == aux2.length) {
								erAux2 = 0;
								comprueba = false;							
							}
						}
					}
				}
				else {							
					erAux++;	
					
					if(erAux == aux.length) {
						erAux = 0;
						comprueba = false;
					}
				}
			}
			return comprueba;
		}
		
		/**
		 * Método que dibuja la carta desde la baraja. La baraja no puede estar vacía.
		 * 		Recibe: nada
		 * 		Devuelve: una carta
		 **/
		public CartaEsp drawCard() {
			
			CartaEsp c = cartas.get(0);
			cartas.remove(0);
	
			return c;
		}
}
