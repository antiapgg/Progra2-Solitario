package solitarioReal1;

import solitarioReal1.Menu;

/*****************************************************************************************
 * 								 	SOLITARIO SWING 							 		 *	
 * 																						 *						
 * Programa usando biblioteca Swing que permita realizar 2 solitarios					 *
 * 																						 *
 * PRIMERO: el de la practica anterior pero usando la baraja Española (40 cartas)	 	 *
 * 																						 *
 * SEGUNDO: 																			 *
 * 									SOLITARIO CLÁSICO 									 *
 * 																						 *
 * 	Al comenzar a jugar se extraen cartas para formar 7 montones de forma:				 *
 * 		- 	1º Monton se pondrá una carta boca arriba									 *
 * 		- 	2º Monton se pondrá una carta boca abajo y encima una boca arriba			 *
 * 		-	3º Monton se pondrán 2 cartas boca abajo y enima una boca arriba			 *
 * 		- 	Seguirá así sucesivamente hasta tener 6 boca abajo y 1 boca arriba			 *
 * 		- 	El resto de cartas quedan boca abajo en el mazo de la baraja				 *
 * 																						 *
 * OBJETIVO: 																			 *
 * 		Colocar todas las cartas en 4 montones nuevos, uno por palo						 *
 * 		Las cartas de cada palo se deberán colocar de forma ascendente comenzando por AS *
 * 		 y términando por el Rey														 *
 * 		Se irán sacando 1 a 1 las cartas que han quedado en el mazo de la baraja hasta	 *
 * 		 que llegue al final. 															 *
 * 		Para cada carta que se saque se podrá:											 *
 * 			- Ponerla en el montón de su palo si es que en éste ya están todas las cartas*
 * 				de número inferior a la suya.											 *
 * 			- Ponerla en alguno de los 7 montones existentes boca arriba si es podible 	 *
 * 				siguiendo las indicaciones de más abajo.								 *
 * 			- Pasarla a un nuevo montón de cartas vistas boca arriba y apiladas según 	 *
 * 				vayan siendo colocadas.													 *
 * 			- Si sacando 1 a 1 las cartas se llega al final de la baraja y no se han 	 *
 * 				logrado poner todas las cartas en 4 montones: 	SOLITARIO NO SALE		 *
 *  																					 *
 * Para poder colocar 1 carta en uno de los 7 montones deberá existir 1 última carta en  *
 * 	ese montón boca arriba que sea de un NUM inmediatamente SUPERIOR a la carta que 	 *
 * 	queremos colocar y de COLOR DISTINTO a la sacada									 *
 *   																					 *
 * En los 7 montones permitido hacer movimientos de cartas cambiandolas de montón siempre*
 * 	que se respete que SOLO PUEDEN DESPLAZARSE CARTAS DESCUBIERTAS y que 1 carta SOLO SE *
 *  PUEDE COLOCAR ENCIMA DE UNA CARTA DE 1 NUM SUPERIOR Y COLOR CONTRARIO A LA Q MOVEMOS *
 *  Si desplazamos una carta descubierta de un monton a otro desplazamos todas de encima *	
 *  																					 *
 * Cuando en 1 monton no hay cartas descubiertas, descubrimos la carta superior boca abaj*
 *  																					 *
 * Si se agotan las cartas de 1 monton se podrá colocar un rey en el hueco vacio 		 *
 *  																					 *
 * Solitario seguirá haciendo movimientos hasta que:									 *
 * 		- Se agoten todas las cartas de la baraja y no hay mas movimientos validos		 *
 * 		- Se resuelva.																	 *
 *  																					 *
 * El programa deberá permitir jugar adecuadamente a los solitarios, gestionando movs	 *
 * 	de cartas  con clics de forma comoda, facil e intuitiva para el usuario.			 *
 *  																					 *
 * La visión del solitario también será adecuada para una pantalla de ordenador habitual *
 * 	resolución mínima de tipo XGA(1024x768) y tendrá un menú en su ventana principal	 *
 *  																					 *
 * 	ENTRADAS DEL MENÚ:																	 * 
 * 		- Archivo. Con los submenús: 													 *
 * 			* Nuevo: crea un nuevo solitario de cualquiera de los 2 tipos				 *
 * 			* Cargar: permitirá abrir un solitario previamente guardado 				 *
 * 			* Salvar: permitirá grabar en el fichero actual el estado del solitario.	 *
 * 					Si es la 1º vez que se graba, entrada Salvar como					 *
 * 			* Salvar como: permite que se grabe el estado del solitario en un fichero	 *
 * 					seleccionado por el usuario.										 *
 * 			* Salir: cerrará el programa ofreciendo al usuario la posibilidad de grabar	 *
 * 					el estado del solitario actual.										 *
 * 		- Editar. Con los submenús:														 *
 * 			* Deshacer: permite deshacer el último movimiento realizado en el solitario  *
 * 					actual. Se podrán deshacer todos los movimientos hechos en el 		 *
 * 					solitario hasta llegar al estado inicial del mismo.					 *
 *			* Hacer: realizar de nuevo un movimiento previamente deshecho. Si no hay 	 *
 *					ningún movimiento deshecho, hacer realizará un movimiento válido en  *
 *					el solitario si es que existe.										 *
 *			* Resolver: intentará resolver el solitario completamente si es que existe 	 *
 *					solución a partir de la posición actual.							 *
 *		- Historial. Con los submenús:													 *
 *			* Estadísticas: Almacenará el número de intentos que se han realizado de cada*
 *					solitario y las veces que se ha terminado con éxito. Solicitará al 	 *
 *					usuario el fichero que contiene las estadísticas, una única vez por  *
 *					sesión, la primera vez que vaya a apuntar un nuevo dato en la serie. *
 *					Si el fichero está vacío, las estadísticas comenzarán de nuevo. Un 	 *
 *					intento de hacer un fichero va desde que se abre uno nuevo, o se 	 *
 *					carga uno previamente realizado parcialmente, hasta que termina con  *
 *					éxito o finaliza sin solución.										 *
 *			* Fichero estadísticas: permite al usuario fijar en cualquier momento de la  *
 *					sesión el fichero de estadísticas, lo que supone cambiarlo si ya 	 *
 *					estaba previamente fijado, bien mediante este menú o bien porque ya  *
 *					se había registrado alguna estadística previamente y se le había 	 *
 *					solicitado al usuario.												 *
 *			* Ayuda: con una descripción adecuada de cómo funciona el programa.			 *
 *  																					 *
 * FORMATO DE LOS ARCHIVOS PARA SALVAR/CARGAR SOLITARIOS: 								 *
 *  																			 		 *
 *		SOLITARIO DE SALTOS:		 													 *   
 *			- 1º Linea: texto "Solitario saltos".										 *
 *			- 2º Linea: con las cartas que restan en el mazo que no se extrajeron aún.	 *
 *			- Siguientes líneas: Una línea por cada montón de cartas descubiertas que hay*
 *					en la mesa, comenzando por el montón de la izquierda. Las cartas se  *
 *					pondrán de abajo a arriba (1º carta inferior, despues la que esta 	 *
 *					encima y así sucesivamente hasta el final del monton).				 *
 *			- Las cartas se representarán por códigos de 2 caracteres 					 *
 *				1º caracter nº de la carta (A, 2-7, S, C, R), y 2º el palo(O, C, E, B)	 *
 *																				 		 *
 *		SOLITARIO CLÁSICO:																 *
 *			- 1º Linea: texto "Solitario clásico".										 *
 *			- 2º Linea: con las cartas que restan en el mazo que no se extrajeron aún.	 *
 *			- Siguientes 7 líneas: 1 linea por cada monton de los 7 del solitario		 *
 *					comenzando por el monton que al comienzo del solitario contenia 1	 *
 *					carta descubierta. Las cartas se pondrán de debajo a arriba, 		 *
 *					(1º carta inferior del monton, despues la de encima, y asi 			 *
 *					sucesivamente hasta el final del monton). Siempre se pondrán primero *
 *					en cada monton las cartas que estan boca abajo. Cuando finalicen las *
 *					cartas cubiertas se pondrá un asterisco.							 *
 *			- Siguientes 4 lineas: Una por cada monton de los palos en el orden:		 *
 *					Clubs, Diamonds, Hearts, Spades.									 *
 *			- Las cartas se representarán por códigos de dos caracteres. 				 *
 *					1º nº de la carta (A, 2-9, T, J, Q, K), y el 2º el palo (C, D, H, S) *
 *																			 		 	 *
 *	FORMATO DEL ARCHIVO DE ESTADÍSTICAS:												 *
 *		- 1º Linea: texto "Solitario saltos".											 *
 *		- 2º Linea: número de intentos realizados del solitario saltos.					 *
 *		- 3º Linea: número de intentos realizados con éxito del solitario saltos.		 *
 *		- 4º Linea: texto "Solitario clásico".											 *
 *		- 5º Linea: número de intentos realizados del solitario clásico.				 *
 *		- 6º Linea: número de intentos realizados con éxito del solitario clásico.		 *
 *																			 			 *
 *	FORMATO ENTREGA:																	 *
 *		Un único fichero Jar que deberá ser ejecutable (definid la Main-class en el 	 *
 *			manifiest.mf), contener el código fuente y todos los recursos requeridos	 *
 *			(las imágenes necesarias). El fichero Jar deberá conservar los directorios	 *
 *			tal y como se usan en el proyecto eclipse. (Opción exportar a Jar de eclipse)*
 *		RECOMENDACIÓN: Ejecutar entrega en vpl para asegurar que la subida es correcta	 *
 *																			 			 *
 *	VPL: 	http://vpl.dis.ulpgc.es														 *
 *																			 			 *
 * 	Jugar solitario clásico en una versión ligeramente diferente a la aquí descrita.	 *
 * 					http://www.zolitario.com/solitario-clasico/ 						 *
 *																			 			 *
 ****************************************************************************************/


public class Main {

	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//Menu gui;

	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		public Main() {
			
			Menu gui = new Menu();
		}
	
		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											FUNCIÓN PRINCIPAL MAIN												//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public static void main(String[] args) {
			
			Main Solitaire = new Main();
		}
}
