package solitarioReal1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Juego {

	
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //											DECLARACION DE VARIABLES					 						 //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private List<Stack<CartaEsp>> pila;
	List<PilaSaltos> pilaSAL;
	List<PilaSaltos> aux;
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //													CONSTRUCTOR													//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Juego() {
		
		pila = new ArrayList<Stack<CartaEsp>>();
	}

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //													 MÉTODOS	 	 										  //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 *  Método que introduce las cartas de la baraja en una lista de pilas
	 *  	Recibe: una baraja
	 *  	Devuelve:  una lista con las pilas que se han formado
	 **/
	public List<Stack<CartaEsp>>juego(BarajaEsp baraja) {

		CartaEsp carta;
		int npila = 0;	
		pila.add(new Stack<CartaEsp>());
			
		for(int i = 0; i < baraja.getNumeroDeCartas();i++){		
			carta = baraja.get(i);		//Cojo una carta
				
			if(pila.get(npila).empty() == true) {	//Si la pila esta vacia 
				pila.get(npila).push(carta);//La anhado
				//comprueba(pila);
			}
			else {
				npila = pila.size();
				npila -= 1;
				//introduceCarta(pila, carta);
				npila = pila.size();
				npila -= 1;	
			}
		}
		
		for(int i = 0; i < pila.size(); i++) {
			aux.add(new PilaSaltos(120));
			for(int j = 0; j < pila.get(i).size(); j++) {
				aux.get(i).add(pila.get(i).pop());
			}
		}

		for(int i = 0; i < pila.size(); i++) {
			pilaSAL.add(new PilaSaltos(120));
			for(int j = pila.get(i).size(); j < 0; j--) {
				pilaSAL.get(i).add(aux.get(i).getComponent(j));
			}
		}
		return pila;
	}
		
	/*
	 * 	Método que introduce una carta en una pila
	 * 		Recibe: una pila y una carta
	 * 		Devuelve: nada
	 */
	public void introduceCarta(List<Stack<CartaEsp>> pila, CartaEsp carta){
		
		CartaEsp aux;
		CartaEsp aux2;
		int h = 0;
		int extra = 0;
		int res;
		res = pila.size();
		h = res;
		h -= 1;
		//Cojo el elemento de la pila sin sacarlo de ella
		aux = pila.get(h).peek();

		if(h > 1) {		//Si hay mas de 1 pila		
			
			if(pila.get(h - 2).size() !=  0) { //Si existe una pila 3 posiciones a la izq de la actual
				extra = h;
				extra -= 2;
				aux2 = pila.get(extra).peek();
				
				if((carta.getNumero()== aux2.getNumero()) || (carta.getPalo()== aux2.getPalo())) {	//Si existe en 3 pos izq
					pila.get(extra).push(carta);	//Anhado la carta
					comprueba(pila);	//Compuebo si se pueden hacer mas mov en la pila
							
				}
				else {
					
					if((carta.getNumero() == aux.getNumero()) || (carta.getPalo()== aux.getPalo())) {	//Si no esta vacio, compruebo si puedo meter la carta
						pila.get(h).push(carta);	//Anhado la carta
						comprueba(pila);	//Compuebo si se pueden hacer mas mov en la pila
					}
					else {
						h += 1;
						pila.add(new Stack<CartaEsp>());
						pila.get(h).push(carta);	//Anhado la carta
						comprueba(pila);	//Compuebo si se pueden hacer mas mov en la pila
					}
				}
			}
		}
		else {
			
			if((carta.getNumero() == aux.getNumero()) || (carta.getPalo()== aux.getPalo())) {	//Si no esta vacio, compruebo si puedo meter la carta
				pila.get(h).push(carta);	//Anhado la carta
				comprueba(pila);	//Compuebo si se pueden hacer mas mov en la pila
			}
			else {
				h += 1;
				pila.add(new Stack<CartaEsp>());
				pila.get(h).push(carta);	//Anhado la carta
				comprueba(pila);	//Compuebo si se pueden hacer mas mov en la pila
			}
		}
	}
		
	/*
	 * 	Método que comprueba si se pueden hacer mas movimientos en la pila, para resolver el solitario
	 * 		Recibe: una lista de pilas
	 * 		Devuelve: una lista de pilas 
	 */
	public List<Stack<CartaEsp>> comprueba(List<Stack<CartaEsp>> pila) {
		
		CartaEsp carta;
		CartaEsp carta1pos;
		CartaEsp carta3pos;
		CartaEsp Aux;
		int ultPila;
		int c3;
		int sol;
			
		if(pila.size() > 1) {
				
			for(int i = 0; i < pila.size()-1; i++) {
					
				ultPila = i;
				ultPila += 1;
					
				if((pila.get(i).size() != 0) && (pila.get(ultPila).size() != 0)) {
					carta1pos = pila.get(i).peek();		//Selecciono 1 carta de la 1 pila a la izq
					carta = pila.get(ultPila).peek();	//Selecciono 1 carta de la pila a la der
						
					if(ultPila > 2){		//Si j es mayor que 3 
						c3 = ultPila;
						c3 -= 3;	//Auxiliar posicion -3
						carta3pos = pila.get(c3).peek();	//Selecciono carta de 3 pilas a la izq
						sol = comp(carta, carta1pos, carta3pos);
							
						if((sol == 1) || (sol == 3)) {
							Aux = pila.get(ultPila).pop();	//Desapilo la carta de la pos actual
							pila.get(c3).push(Aux);		//La apilo en 3 pos menos
							elimina(pila);	//Borra huecos en blanco 
							comprueba(pila);
						}
						else {
							
							if(sol == 2) {
								Aux = pila.get(ultPila).pop();	//Desapilo la carta 
								pila.get(i).push(Aux);		//La apilo
								elimina(pila);		//Borra huecos en blanco 
								comprueba(pila);	//Volver a comprobar la pila
							}
						}
					}
					else {
						
						if((carta.getNumero() == carta1pos.getNumero()) || (carta.getPalo() == carta1pos.getPalo())) {
							Aux = pila.get(ultPila).pop();	//Desapilo la carta 
							pila.get(i).push(Aux);	//La apilo
							elimina(pila);		//Borra huecos en blanco 
							comprueba(pila);
						}
					}
				}
				else {
					elimina(pila);		//Borra huecos en blanco 
					comprueba(pila);	
				}	
			}
		}	
		return pila;
	}
		
	/*
	 * 	Método que comprueba si se puede apilar y en que posición 
	 * 		Recibe: 3 cartas (la que tenemos que anhadir, la de la posición anterior, y la de 3 posiciones atras)
	 * 		Devuelve: un entero
	 */
	public int comp(CartaEsp carta, CartaEsp carta1pos, CartaEsp carta3pos) {
		
		int sol = 0;
		
		if((carta.getNumero() == carta1pos.getNumero()) || (carta.getPalo() == carta1pos.getPalo())) {
			sol = 2;	//Si sol = 2, apilo en la posicion anterior
		}
		
		if((carta.getNumero() == carta3pos.getNumero()) || (carta.getPalo() == carta3pos.getPalo())){
			++sol;	//Si sol = 1, apilo 3 posiciones anteriores
		}				//Si sol = 3, apilo en 3 posiciones anteriores
		return sol;
	}
		
	/*
	 * 	Método que elimina las pilas que esten vacias
	 * 		Recibe: una lista de pilas
	 * 		Devuelve: una lista de pilas
	 */
	public List<Stack<CartaEsp>> elimina(List<Stack<CartaEsp>> pila){
		
		for(int i = 0; i < pila.size(); i++) {
			
			if(pila.get(i).empty() == true) {	//Si la pila esta vacia	
				pila.remove(i);
			}
		}
		return pila;
	}
		
	/*
	 *	Método que indica cuantas pilas hay
	 *		Recibe: nada
	 *		Devuelve: un entero que indica el tamanho de la lista
	 */
	public int getSize() {
		return pila.size();
	}
		
	/*
	 * 	Método que indica el tamanho de la pila
	 * 		Recibe: un entero (la posicion de la pila)
	 * 		Devuelve: un entero que indica el tamanho de la pila
	 */
	public int getN(int x) {
		return pila.get(x).size();
	}
		
}
