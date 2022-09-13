package solitarioReal1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Card class to store the information of single card
 * @member {Suit} suit The suit of the card (Spades,Hearts,Diamonds,Clubs)
 * @member {Integer} value The value of the card (1->13)
 */
public class CartaFranc extends JPanel{
	
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public int value;
		public palosFranc palosColor;
		private BufferedImage image;
		private BufferedImage tapaImg;
		boolean isReversed;
		Point positionOffset;
		
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR													//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public CartaFranc(int value, palosFranc suit) {
			
			this.value = value;
			this.palosColor = suit;		
			isReversed = false;
			
			try {
				// Load the image for the current file
				URL url = getClass().getResource("/resources/" + this.toString() + ".jpg");
				image = ImageIO.read(url);
				
				// Load the backimage
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
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
		/**
		 * Método que enumera los palos de la baraja Francesa para almacenarlos. Almacena también si son rojas o negras.
		 **/
		public enum palosFranc {
			S(1, false),
			H(2, true),
			D(3, true),
			C(4, false);
			
			public int value;
			public boolean isRed;
			
			private palosFranc(int value, boolean isRed) {
				this.value = value;
				this.isRed = isRed;
			}
		}
				
		/**
		 * Método que convierte el valor de la carta en el palo que le pertenece (String).
		 * 		Recibe: el número de la carta.
		 * 		Devuelve: un String.
		 **/
		public static String valueStringFran(int value) {
			
			if(value == 10) return "T";
			if(value == 12) return "J";
			if(value == 13) return "Q";
			if(value == 14) return "K";
			if(value == 1) return "A";
			
			// Valor entre 2 y 9
			return Integer.toString(value);
		}

		
		/**
		 * Método que convierte el palo de la cartas (String) en el entero que le corresponde.
		 * 		Recibe: el número de la carta. 
		 **/
		public static int valueIntFran(String value) {
			
			if(value.equals("T")) return 10;
			if(value.equals("J")) return 12;
			if(value.equals("Q")) return 13;
			if(value.equals("K")) return 14;
			if(value.equals("A")) return 1;
			
			return Integer.parseInt(value);
		}
		
		
		/**
		 * Método que pasa a String la carta.
		 * 		Recibe: nada.
		 * 		Devuelve: la carta transformada en String.
		 **/
		public String toString() {
			
			return valueStringFran(value) + "_" + palosColor.name();
		}

		
		/*
		 * Returns a string that can be used to re-initialize the card
		 * @return {String} Class properties, " of " separated.
		 */
		public String saveAsString() {
			
			return valueStringFran(value) + "_" + palosColor.name() + "_" + isReversed;
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
