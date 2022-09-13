package solitarioReal1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CartaEsp extends JPanel{
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		private String carta;
		private char numero;
		private char palo;
		private BufferedImage image;
		private BufferedImage tapaImg;
		boolean isReversed;
		int value;
		Point positionOffset;
			
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
		public CartaEsp(String carta) {
			
			this.carta = carta;
			this.numero = carta.charAt(0);
			this.palo = carta.charAt(carta.length() - 1);
			this.isReversed = false;
			
			isReversed = false;
			
			try {
				//Cargar imagen de la carta
				URL url = getClass().getResource("/resources/" + this.palo + "_" + this.numero + ".jpg");
				image = ImageIO.read(url);
				
				//Cargar la tapa de la carta
				url = getClass().getResource("/resources/back.png");
				tapaImg = ImageIO.read(url);
				
				setBounds(0, 0, image.getWidth(), image.getHeight());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			positionOffset = new Point(0,0);
			setSize(new Dimension(100, 145));
			setOpaque(false);
			return ;
		}
			
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													 MÉTODOS		 										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/**
		 *  Método que devuelve la carta.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public String getCarta(){
			
			return carta;		
		}
		
		/**
		 *  Método que pone la carta.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public void setCarta(String carta){
			
			this.carta = carta;		
		}
		
		/**
		 *  Método que devuelve el número de la carta.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public char getNumero() {
			
			return numero;
		}
		
		/**
		 *  Método que devuelve el palo de la carta.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public char getPalo() {
			
			return palo;
		}
		
		/**
		 *  Método que transforma la carta en String.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public String carStr() {
			
			return carta.toString();
		}
		
		/**
		 *  Método que compara si 2 cartas son iguales.
		 *  	Recibe: una carta.	
		 *  	Devuelve: un booleano que dice si son iguales o no.
		 **/
		public boolean equals(CartaEsp carta) {		
			
			return this.carta.equals(carta.getCarta());
		}
		
		/**
		 *  Método que compara si el número de una carta es igual al de otra.
		 *  	Recibe: un char con el numero de la carta.
		 *  	Devuelve: un booleano que dice si son iguales o no.
		 **/
		public boolean equalsNumero(char num) {	
			
			return this.numero == num;
		}
		
		/**
		 *  Método que compara si el palo de una carta es igual al de otra.
		 *  	Recibe: un  char con el palo de la carta.
		 *  	Devuelve: un booleano diciendo si son iguales o no.
		 **/
		public boolean equalsPalo(char palo) {	
			
			return this.palo == palo;
		}
		
		/**
		 *  Método que devuelve el tamanho de la carta.
		 *  	Recibe: nada.
		 *  	Devuelve: nada.
		 **/
		public int sizze() {
			
			return carta.length();
		}	
		
		/**
		 * Método que muestra la carta boca abajo.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void hide() {
			
			isReversed = true;
		}
	
		/**
		 * Método que muestra la carta boca arriba.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void show() {
			
			isReversed = false;
		}
		
		/*
		 * Método que enumera los palos de la baraja Española para almacenarlos.
		 */
		public enum palosEsp {
			O(1),
			E(2),
			C(3),
			B(4);
			
			public int value;
			
			private palosEsp(int value) {
				this.value = value;
			}
		}
		
			
		/*
		 * Método que convierte el valor de la carta en el palo que le pertenece (String)
		 */
		public static String valueStringEsp(int value) {
								
			if(value == 12) return "R";
			if(value == 11) return "C";
			if(value == 10) return "S";
			if(value == 1) return "A";
			
			// Value between 2 and 7
			return Integer.toString(value);
		}
		
		/*
		 * Método que convierte el palo de la cartas (String) en el entero que le corresponde
		 * @param {String} value The value of the card 
		 */
		public static int valueIntEsp(String value) {
			
			if(value.equals("A")) return 1;
			if(value.equals("S")) return 10;
			if(value.equals("C")) return 11;
			if(value.equals("R")) return 12;
			
			return Integer.parseInt(value);
		}
		
		 
		/*
		 * Método que pasa a String la carta
		 */
		public String toString() {
			return this.numero + "_" + this.palo;
		}

		
		/*
		 * Returns a string that can be used to re-initialize the card
		 * @return {String} Class properties, " of " separated.
		 **/
		public String saveAsString() {
			return this.numero + "_" + this.palo + " _ " + isReversed;
		}
		

		/**
		 * Método que dibuja la carta.
		 **/
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			BufferedImage img = image;
			if(isReversed) img = tapaImg;

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	
		

}
