package solitarioReal1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import solitarioReal1.Pila.PileType;

public class Menu extends JFrame implements  MouseListener, MouseMotionListener {
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		private JPanel panelPrincipal;
		JPanel areaJuego;
		JPanel columnas;
		JPanel topColumnas;
		JLayeredPane lp;
		SolitarioSaltos jump;
		SolitarioClasico game;
		Pila tempPile;
		PilaSaltos tempPileJ;
		Point mouseOffset;
		List<MouseEvent> movDeshacer = new ArrayList<MouseEvent>();
		List<Point> movDeshacerPo = new ArrayList<Point>();
		ArrayList<String[]> arrayBarajas = new ArrayList<String[]>();
		List<Pila> neeed = new ArrayList<Pila>();
		List<int[]> movs = new ArrayList<int[]>();
		int tipoJuego = 0; 
		String[] auxiliar;
		String[] auxiliar2;
		String[] auxiliar3;
		String[] barajaComp;
		String[] res;
		String nomFich;
		String nombreA = "";
		int numSolSaltos = 0;
		int numSolClasico = 0;
		int winSolSaltos = 0;
		int winSolClasico = 0;
		int numPilas = 1;
		int fin = 0;
		
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR												   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public Menu () {		
			
			creaMenu();
		}
		

	  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													 MÉTODOS		 										   //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Función que crea el Menu.
		 * 		Recibe: nada.	
		 * 		Devuelve: nada.
		 **/
		private void creaMenu() {
			
			new JFrame("Menu");
			panelPrincipal = (JPanel)getContentPane();
			//Añado un borde
			panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			//Para que cuando le de a la x salga
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Tamaño de la ventana
			setSize(1024,768);
			//La centro en mi pantalla
			setLocation(200,0);
			//Permito que se pueda redimensionar
			setResizable(true);
			
			creaBarraMenu();
			
			//Le pongo un título
			setTitle("Solitario");
			//Cambio color al fondo
			panelPrincipal.setBackground(new Color(0,113,26));
							
			//contenedor.setLayout(new BorderLayout());
			setVisible(true);
		}
		
		
		/**
		 * Función que crea la barra del Menu.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		private void creaBarraMenu() {
			
			final int SHORTCUT_MASK =
		            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
			
			//Creo la barra de Menu
			JMenuBar barraMenu = new JMenuBar();
			setJMenuBar(barraMenu);
			
			//Creo el menú Archivo
			JMenu menuArchivo = new JMenu ("Archivo");
			//Lo agrego a la barra de menu
			barraMenu.add (menuArchivo);	
				
				//Creo el submenú Nuevo			Lo convertimos en menú
				JMenu menuNuevo = new JMenu("Nuevo");		
					//Creo las opciones del submenú
					JMenuItem solitarioSaltos = new JMenuItem ("Solitario Saltos");
					JMenuItem solitarioClasico = new JMenuItem ("Solitario Clásico");
					//Los agrego al menu Nuevo
					menuNuevo.add(solitarioSaltos);
					menuNuevo.add(solitarioClasico);	
				//Lo añado al menu Archivo
				menuArchivo.add(menuNuevo);		
			//Añado una rayita separadora.
			menuArchivo.add(new JSeparator()); 
				//Creo las opciones del menu Archivo	
				JMenuItem cargar = new JMenuItem ("Cargar");
				cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
				JMenuItem salvar = new JMenuItem ("Salvar");
				salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
				JMenuItem salvarComo = new JMenuItem ("Salvar como");
				salvarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
				JMenuItem salir = new JMenuItem ("Salir");
				salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
				//Los agrego al menu Archivo
				menuArchivo.add(cargar);
				menuArchivo.add(salvar);
				menuArchivo.add(salvarComo);
				menuArchivo.add(salir);
				
			//Creo el menu Editar
			JMenu menuEditar = new JMenu ("Editar");
			//Lo agrego a la barra de Menu
			barraMenu.add (menuEditar);
					//Creo las opciones del menu Editar	
					JMenuItem deshacer = new JMenuItem ("Deshacer");
					JMenuItem hacer = new JMenuItem ("Hacer");
					JMenuItem resolver = new JMenuItem ("Resolver");
					//Los agrego al menu Editar
					menuEditar.add(deshacer);
					menuEditar.add(hacer);
					menuEditar.add(resolver);
					
			//Creo el menu Historial
			JMenu menuHistorial = new JMenu ("Historial");
			//Lo agrego a la barra de Menu
			barraMenu.add (menuHistorial);		
					//Creo las opciones del menu Historial	
					JMenuItem estadisticas = new JMenuItem ("Estadisticas");
					JMenuItem fichero = new JMenuItem ("Fichero");
					//Los agrego al menu Historial
					menuHistorial.add(estadisticas);
					menuHistorial.add(fichero);

			//Creo el menu Ayuda
			JMenuItem menuAyuda = new JMenuItem ("Ayuda");
			//Lo agrego a la barra de Menu
			barraMenu.add (menuAyuda);
			
			
			//Creo de los tres escuchadores
			Escuchador esc;   //Son clases internas a nuestro JFrame

			//Instancio escuchadores
			esc=new Escuchador ();
			
			//asocio el escuchador a cada menu 
			solitarioSaltos.addActionListener (esc);
			solitarioSaltos.setActionCommand ("saltos");
			solitarioClasico.addActionListener(esc);
			solitarioClasico.setActionCommand ("clasico");
			cargar.addActionListener(esc);
			cargar.setActionCommand ("cargar");
			salvar.addActionListener(esc);
			salvar.setActionCommand ("salvar");
			salvarComo.addActionListener(esc);
			salvarComo.setActionCommand ("salvarcomo");
			salir.addActionListener (esc);
			salir.setActionCommand ("salir");
			deshacer.addActionListener (esc);
			deshacer.setActionCommand ("deshacer");
			hacer.addActionListener (esc);
			hacer.setActionCommand ("hacer");
			resolver.addActionListener (esc);
			resolver.setActionCommand ("resolver");
			estadisticas.addActionListener (esc);
			estadisticas.setActionCommand ("estadisticas");
			fichero.addActionListener (esc);
			fichero.setActionCommand ("fichero");
			menuAyuda.addActionListener (esc);
			menuAyuda.setActionCommand ("ayuda");
		}

		
		/**
		 * Escuchador de la barra del Menu.
		 **/
		class Escuchador implements ActionListener{
			public void actionPerformed (ActionEvent e){
				
				JMenuItem b=(JMenuItem)e.getSource();

				if (b.getActionCommand()== "saltos"){
					
					saltos();
				}
				if (b.getActionCommand()== "clasico"){
					solitarioClasico();
				}
				if (b.getActionCommand()== "cargar"){
					cargar();
				}
				if (b.getActionCommand()== "salvar"){
					salvar();
				}
				if (b.getActionCommand()== "salvarcomo"){
					salvarComo();
				}
				if(b.getActionCommand()== "salir") {
					salir();
				}
				if (b.getActionCommand()== "deshacer"){
					System.out.println("ESCUCHO");
					deshacer();
				}
				if (b.getActionCommand()== "hacer"){
					hacer();
				}
				if (b.getActionCommand()== "resolver"){
					resolver();
				}
				if (b.getActionCommand()== "estadisticas"){
					try {
						estadisticas();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (b.getActionCommand()== "fichero"){
					try {
						fichero();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (b.getActionCommand()== "ayuda"){
					ayuda();
				}
			}
		}
		
		
		/**
		 * Función Saltos, es a la primera que llamamos para ejecutar SolitarioSaltos, esta coge la baraja y llama a la funcion SolitarioSaltos.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 */
		private void saltos() {
			
	    	tipoJuego = 1;
	    	String text = "";
	    	JTextArea tx = new JTextArea();
	    	tx.setPreferredSize(new Dimension(450,150));
	    	tx.setLineWrap(true);
	        tx.setWrapStyleWord(false);
	        Icon icono = new ImageIcon(getClass().getResource("/resources/naipes.jpg"));
	    	JOptionPane.showMessageDialog(null, new JScrollPane(tx), "Introduzca una baraja:",
	    	        JOptionPane.WARNING_MESSAGE, icono);
	    	text = tx.getText();
	    	
	    	if(text==null) {
	    		System.out.println("La operacion ha sido cancelada");
	    		return;
	    	}else {
	    		solSalEntrada(text);
	    	}
	    
	    	for(int k = 0; k < arrayBarajas.size(); k++) {
	    	  	
	    		System.out.println("Baraja número "+(k+1));
	    		JOptionPane.showMessageDialog(this, "Baraja número "+(k+1)+" del Solitario Saltos.\n Empieza el juego.");
	    		
	    		solitarioSaltos(arrayBarajas.get(k));
	    		
	    	}
		}
		
		
	    /**
	     * Función que inicia el juego Solitario Saltos a la izquierda.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    public void solitarioSaltos(String[] baraja)  {
    	
    		jump = new SolitarioSaltos(baraja);
    		numSolSaltos++;

	    	setLayout(new BorderLayout());
			
			areaJuego = new JPanel();
			areaJuego.setBackground(new Color(128, 0, 0));
			areaJuego.setOpaque(true);
			areaJuego.setLayout(new BoxLayout(areaJuego, BoxLayout.PAGE_AXIS));
					
			//Añadimos multiples columnas en la misma fila
			FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
			flow.setAlignOnBaseline(true);
			
			//Añadimos el panel de las columnas
			columnas = new JPanel();
			columnas.setOpaque(false);
			columnas.setLayout(flow);
			columnas.setMinimumSize(new Dimension(200, 900));
			
			//Añadimos el panel de las columnas de arriba
			FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
			topFlow.setAlignOnBaseline(true);
			
			topColumnas = new JPanel();
			topColumnas.setOpaque(false);
			topColumnas.setLayout(topFlow);
				
			areaJuego.add(topColumnas);
			areaJuego.add(columnas);
			
			add(areaJuego);
			
			lp = getLayeredPane();
			setVisible(true);

			inicializaJump();

	    }
	    
	    
	    /**
	     * Función que inicia el Juego del Solitario Clasico.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void solitarioClasico()  {
	    	
	    	tipoJuego = 2;
	    	SolitarioClasico game = new SolitarioClasico();
	    	numSolClasico++;
	    	this.game = game;
	    	setLayout(new BorderLayout());
			
			areaJuego = new JPanel();
			areaJuego.setBackground(new Color(55, 128, 47));
			areaJuego.setOpaque(true);
			areaJuego.setLayout(new BoxLayout(areaJuego, BoxLayout.PAGE_AXIS));
					
			//Añadimos multiples columnas en la misma fila
			FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
			flow.setAlignOnBaseline(true);
			
			//Añadimos el panel de las columnas
			columnas = new JPanel();
			columnas.setOpaque(false);
			columnas.setLayout(flow);
			columnas.setMinimumSize(new Dimension(200, 900));
			
			// Añadimos el panel de las columnas de arriba
			FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
			topFlow.setAlignOnBaseline(true);
			
			topColumnas = new JPanel();
			topColumnas.setOpaque(false);
			topColumnas.setLayout(topFlow);
				
			areaJuego.add(topColumnas);
			areaJuego.add(columnas);
			
			add(areaJuego);
			
			lp = getLayeredPane();
			setVisible(true);
						
	    	inicializa();

	    }
	    
	    
	    //TODO
	    /*
	     * Función que carga un solitario previamente guardado.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void cargar()  {
	    	
	    	//game.load();
	    	//Funciones funcion = new Funciones();
			//funcion.search();
	    	
	    	FileChooserDemo fc = new FileChooserDemo();
	    	fc.load();
	    	add(fc);
	    	setVisible(true);
	    	fc.setVisible(false);
			validate();
			return;
	    }
	    
	
	    /*
	     * Función que guarda la partida actual en un fichero, si es la 1º vez debe guardar como.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void salvar()  {
	    	
	    	String nombre;
	    	
	    	if(nombreA.equals("")) {
	    		JOptionPane.showMessageDialog(this, "Es la primera vez que guarda el juego.\nDebe hacerlo con Salvar como.");
	    		salvarComo();
	    	}
	    	else {
		    	File archivo = new File(nombreA);
		    	
		    	if (!archivo.exists()) {
		    		JOptionPane.showMessageDialog(this, "El archivo no existe.");
		    		salvarComo();
		    	}
		    	else {
		    		Funciones funcion = new Funciones();
		    		funcion.save(nombreA, tipoJuego);
					JOptionPane.showMessageDialog(this, "Juego Guardado.");
					return;
		    	}
	    	}
	    }
	
	  
	    /*
	     * Metodo que guarda un solitario con un nombre especifico.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void salvarComo()  {
	    	
	    	nombreA = "";
	    	FileChooserDemo fc = new FileChooserDemo();
	    	nombreA = fc.saveAs(tipoJuego);
	    	add(fc);
	    	setVisible(true);
	    	fc.setVisible(false);		
			JOptionPane.showMessageDialog(this, "Juego Guardado.");
			return;
	    }
	    
	    
	    /**
	     * Metodo que sale del programa. Antes de salir debe dar la opción de guardar.
	     * 		Recibe: nada.	
	     * 		Devuelve: nada.
	     **/
	    private void salir()  {
	    	
	    	 int seleccion = JOptionPane.showOptionDialog(this, "¿Desea guardar los datos antes de salir?", "Atención!", 
	    			 				1, 3, null, new Object[]{"Si", "No","Cancelar"}, "Si"); 
          
             switch(seleccion){ 
                 case 0://Si elegimos "Si" 
                     salvarComo();
                     this.dispose();
                     System.exit(0);
                     break; 
                 case 1://Si elegimos "No" 
                      this.dispose();
                      System.exit(0);
                     break; 
             }
	    }
	    
	    
	    /*
	     * Metodo que crea una lista que almacena todos los movimientos que hacemos
	     */
	    private void deshacer()  {
	    	
	    	deshacerMov();
	    }
	    
	    
	    /*
	     * Metodo que rehace el metodo deshecho
	     */
	    private void hacer()  {

	    }
	    
	    
	    /*
	     * Función que resuelve el juego.
	     */
	    private void resolver()  {

	    }
	    
	    
	    /**
	     * Función que da las estadísticas del juego.	Guardo en el archivo.
	     * 		Recibe: nada.
	     * 		Devuelve: nada. 
	     **/
	    private void estadisticas() throws IOException  {
	    	
	    	if(nomFich == "") {
	    		
					fichero();
	    	}
	    	else {
	    		int datos[] = new int[4];
	    		datos[0] = numSolSaltos;
	    		datos[1] = winSolSaltos;
	    		datos[2] = numSolClasico;
	    		datos[3] = winSolClasico;
	    		 Funciones funcion = new Funciones();
	    		 funcion.escribFich(nomFich, datos);
	    	}
	    	
	    }
	    
	    
	    /**
	     * Función que crea un fichero del juego.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void fichero() throws IOException  {
	    	
	    	nomFich = "";
	    	FileChooserDemo fc = new FileChooserDemo();
	    	int datos[] = new int[4];
    		datos[0] = numSolSaltos;
    		datos[1] = winSolSaltos;
    		datos[2] = numSolClasico;
    		datos[3] = winSolClasico;
	    	nomFich = fc.saveFich(datos);
	    	add(fc);
	    	setVisible(true);
	    	fc.setVisible(false);		
			JOptionPane.showMessageDialog(this, "Fichero Guardado.");
			return;

	    }
	   
	    
	    /**
	     * Función que muestra una ventana con las instrucciones del juego.
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    private void ayuda()  {
	    	JOptionPane.showMessageDialog(this,
	    			  "<html><b>Juego Solitario\n"
	    			+ "Tienes disponibles 2 opciones de juego:\n"
	    			+ "		 1. Solitario Saltos a la Izquierda.\n"
	    			+ "			El objetivo del juego es apilar todas las cartas en una sola pila, pudiendo:\n"
	    			+ "				- Apilarlas en la pila anterior, si coincide el número o el palo de la carta actual con la de la pila.\n"
	    			+ "				- Apilarlas en una pila situada 3 posiciones antes, si coincide el número o el palo de la carta actual con el de la pila.\n"
	    			+ "				- Si puedes apilar una carta en la posición anterior, o 3 posiciones antes, debes apilarla en 3 posiciones antes.\n "
	    			+ "				- Apilarlas en una pila nueva, si no se puede en ninguna más.\n"
	    			+ "		 2. Solitario Clásico.\n"
	    			+ "			El objetivo del juego es apilar todas las cartas por palo en los huecos situados arriba a la derecha, del As al Rey.\n"
	    			+ "				- Solo puedes mover las cartas que estan boca arriba.\n"
	    			+ "				- Si hay algún As, sitúalo en el hueco correspondiente.\n"	    			
	    			+ "				- Si puedes, mueve las cartas intentando hacer una escalera en orden descendiente e intercalando los colores de las cartas.\n "
	    			+ "				- Haz eso hasta que no puedas mover nada.\n"
	    			+ "				- Cuando no puedas hacer mas movimientos, saca una carta del mazo, e intenta realizar mas movimientos en las pilas \n"
	    			+ "					o situarla en los huecos superiores.\n"
	    			+ "				- Cada vez que dejes una carta boca a bajo al descubierto, debes darle la vuelta para seguir jugando.\n");
	    	return;
	    }
		
	    
		/**
		 * Función que añade las cartas desde SolitarioClasico al Menu (Interfaz Grafica)
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		private void inicializa() {
			
			int j = 0;
			topColumnas.removeAll();
			columnas.removeAll();
			
			//Añado un listener a cada una de las 52 cartas
			for(CartaFranc c: game.baraja.cartas) {	
				c.addMouseListener(this);					
				c.addMouseMotionListener(this);	
			}
			
			game.setupGame();
			
			for(Pila p : game.pila) {
				columnas.add(p);
			}
			
			topColumnas.add(game.drawPila);
			topColumnas.add(game.getPila);
			
			for(Pila p : game.finalPila) {
				topColumnas.add(p);
			}
			
			validate();
		}
		
		
		/**
		 * Función que añade las cartas desde SolitarioSaltos al Menu (Interfaz Grafica)
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		private void inicializaJump() {
			
			topColumnas.removeAll();
			columnas.removeAll();

			// Añado un listener a cada una de las 40 cartas
			for(int i = 0; i < jump.baraja.getNumeroDeCartas(); i++) {
				CartaEsp c = jump.baraja.get(i);
		
				c.addMouseListener(this);
				c.addMouseMotionListener(this);		
			}

			jump.setupJump(numPilas);
			
			for(PilaSaltos p : jump.pila) {
				columnas.add(p);
			}
			columnas.add(jump.drawPila);
			topColumnas.add(jump.drawPila);
			topColumnas.add(jump.getPila);
			
			for(PilaSaltos p : jump.finalPila) {
				topColumnas.add(p);
			}
			validate();
			
			if(jump.fin() == true) {
				
			}
		}
		
		
		/**
		 * Método que comprueba que el String de entrada sea correcto.
		 * 		Recibe: el String de entrada.
		 * 		Devuelve: nada.
		 **/
		public void solSalEntrada(String text) {
			
			int cont = 0;
			int contLineas = 0;
			auxiliar = text.split("\n" );	//Todas las líneas 
			
			bucle:
			while(auxiliar.length > cont) {
				
				do {
					System.out.println("Contador: "+cont+" Tam Auxiliar: "+auxiliar.length);
					auxiliar2 = auxiliar[cont].split(" ");
			
					if(contLineas == 0) {
						auxiliar3 = auxiliar2;
					}
					
					if(auxiliar2.length == 1) {
						
						if(auxiliar2[0].equals("#")) {
							break bucle;
						}
						else {
							JOptionPane.showMessageDialog(this,"Entrada incorrecta.");
							return;
						}
					}	
					cont++;
					
					if(contLineas == 1) {
						res = new String[auxiliar3.length + auxiliar2.length];		 
						System.arraycopy(auxiliar3, 0, res, 0, auxiliar2.length);
						System.arraycopy(auxiliar2, 0, res, auxiliar3.length, auxiliar2.length);					
						contLineas++;
					}
					else contLineas++;
					
				}while(contLineas != 2);
				
				if(contLineas == 2 && res.length == 40) {
					contLineas = 0;
					arrayBarajas.add(res);
				}
				else {
					JOptionPane.showMessageDialog(this,"Baraja erronea.");
				}
			}
		}
		
		
		/**
		 * Función que resetea el juego del SolitarioClasico y SolitarioSaltos completo.
		 * 		Recibe: nada.
		 * 		Devuelve: nada;
		 **/
		public void reset() {
			
			if(tipoJuego == 1) {
				jump.resetCards();
				inicializaJump();
			}
			
			if(tipoJuego == 2) {
				game.resetCards();
				inicializa();
			}
			repaint();
		}
	

		/**
		 * Método que se encarga de deshacer los movimientos realizados por el jugador.
		 * 		Recibe: nada.
		 * 		Devuelve: nada.
		 **/
		public void deshacerMov() {
			
			int tam = movDeshacer.size() - 1;
			MouseEvent e  = movDeshacer.get(movDeshacer.size()-1);
			Point posI = movDeshacerPo.get(movDeshacerPo.size()-2);
			Point posF = movDeshacerPo.get(movDeshacerPo.size() -1);
			CartaFranc c = (CartaFranc) e.getComponent();
			Pila p  = (Pila) c.getParent();
			p.remove(c);
			Pila w = neeed.get(neeed.size() - 1);
			w.addCarta(c);
			mouseOffset = e.getPoint();
			posI.x = e.getLocationOnScreen().x - posF.x - mouseOffset.x ;
			posI.y = e.getLocationOnScreen().y - posF.y - mouseOffset.y ;
			
			tempPile = p.divide(c);
			c.setLocation(posI );
	/*		
			//lp.remove(tempPile);
			movDeshacer.remove(tam);
			movDeshacerPo.remove(tam);
			tempPile.setLocation(posI);*/
			lp.remove(p);
			//lp.add(p);
			//lp.remove(w);
			lp.add(w);
			
			repaint();	
		}
		
		
		/*
		 * Método fin.
		 */
		public void fin() {
			
			if(jump.fin()) {
				
			}
		}
	
		
		/**
		 * Método que se encarga de gestionar los movimientos de arrastrar las cartas.
		 **/
		public void mouseDragged(MouseEvent e) {
			
			if(tipoJuego == 2) {
				
				if(tempPile != null) {				
					Point pos = getLocationOnScreen();
					pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
					pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;
					
					tempPile.setLocation(pos);
				}
			}
			
			if(tipoJuego == 1) {
				
				if(tempPileJ != null) {
					Point pos = getLocationOnScreen();
					pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
					pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;
					
					tempPileJ.setLocation(pos);
				}			
			}
			repaint();
		}

		
		/**
		 * Método que gestiona los movimientos del raton (funciona constantemente).
		 **/
		public void mouseMoved(MouseEvent e) {
			
		}
		
		
		/**
		 * Método que gestiona los clicks sobre las cartas.
		 * 	Para mostrar las cartas ocultas, o del mazo o de la mesa de juego.
		 **/
		public void mouseClicked(MouseEvent e) {
			
			if(tipoJuego == 2) {
				
				if(e.getComponent() instanceof CartaFranc) {	
					System.out.println("CLICK PILA");
					CartaFranc c = (CartaFranc)e.getComponent();
					Pila p = (Pila)c.getParent();
					System.out.println("Mueves algo MOUSE CLICKED");		
					switch(p.type) {
					
						case Draw:
							System.out.println("CLICK PILA DRAW");
							game.drawCard();	//Muestras la carta del mazo
						break;
						
						case Normal:
							System.out.println("CLICK PILA NORMAL");
							game.clickPile(p);	//Muestras la carta de la mesa de juego
						break;

					}	
					repaint();
				}
			}
			
			if(tipoJuego == 1) {
				
				if(e.getComponent() instanceof CartaEsp) {
					CartaEsp c = (CartaEsp)e.getComponent();
					PilaSaltos d = (PilaSaltos)c.getParent();
					
					switch(d.type) {
					
						case Draw:
							System.out.println("CLICK PILA DRAW");
							jump.drawCard();	//Muestras las cartas del mazo
						break;
						
						case Get:
							System.out.println("CLICK PILA GET");
							jump.turnGetPile();	//Le das la vuelta al mazo descubierto
						break;
					}	
					repaint();
				}
			}
		}


		/**
		 * Método que "Presiona la carta" (Coge la carta para moverla (drag))
		 * 	Presionas la carta del mazo para llevarla a las pilas de juego, y la crta de las pilas de juego para llevarla a otra pila de juego.
		 **/
		public void mousePressed(MouseEvent e) {
			
			if(tipoJuego == 2) {
					neeed.add((Pila)e.getComponent().getParent());
					Point posi = getLocationOnScreen();
					movDeshacerPo.add(posi);
					movDeshacer.add(e);
					
				if(e.getComponent() instanceof CartaFranc) {
					CartaFranc c = (CartaFranc)e.getComponent();
					
					//No hace nada si la carta esta boca abajo
					if(c.isReversed)
						return;
					
					Pila p  = (Pila)c.getParent();
					
					if(p.cards.isEmpty() || p.type == PileType.Final) return;
					
					tempPile = p.divide(c);
	
					lp.add(tempPile, JLayeredPane.DRAG_LAYER);
					Point pos = getLocationOnScreen();
					mouseOffset = e.getPoint();
					pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
					pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;
					
					tempPile.setLocation(pos);
					
					repaint();
				}
			}
			else {
				
				if(tipoJuego == 1) {
					
					Point posi = getLocationOnScreen();
					movDeshacerPo.add(posi);
					
					if(e.getComponent() instanceof CartaEsp) {
						CartaEsp c = (CartaEsp)e.getComponent();
						
						//No hace nada si esta boca abajo
						if(c.isReversed)
							return;
						
						PilaSaltos d  = (PilaSaltos)c.getParent();
						
						if(d.cards.isEmpty()) return;
						
						tempPileJ = d.divide(c);
			
						lp.add(tempPileJ, JLayeredPane.DRAG_LAYER);
		
						Point pos = getLocationOnScreen();
						mouseOffset = e.getPoint();
						pos.x = e.getLocationOnScreen().x - pos.x - mouseOffset.x;
						pos.y = e.getLocationOnScreen().y - pos.y - mouseOffset.y;
						
						tempPileJ.setLocation(pos);
						
						repaint();
					}
				}
			}
		}

		
		/**
		 * Método que deja la carta en la pila correspondiente (la suelta)
		 **/
		public void mouseReleased(MouseEvent e) {
			
				if(tipoJuego == 2) {
					Point posi = getLocationOnScreen();
					movDeshacerPo.add(posi);
					
					if(tempPile != null) {
						
						Point mousePos = e.getLocationOnScreen();
						boolean match = false;
						
						//Comprueba si la pila puede fusionarse con la pila sobre la que se tira.
						ArrayList<Pila> droppable = new ArrayList<Pila>(game.pila);
						droppable.addAll(game.finalPila);
						for(Pila p: droppable) {
							Point pilePos = p.getLocationOnScreen();
							Rectangle r = p.getBounds();
							r.x = pilePos.x;
							r.y = pilePos.y;
							
							if(r.contains(mousePos) && p.movimientoValido(tempPile)) {
								p.merge(tempPile);
								match = true;
								break;
							}
						}						
						//Retrocede si no se encuentra una fusión
						if(!match) tempPile.parent.merge(tempPile);
						
						lp.remove(tempPile);
						tempPile = null;
	
						repaint();
						
						if(game.checkWin()) {
							JOptionPane.showMessageDialog(this, "¡Has ganado esta partida, Felicidades!");
							winSolClasico++;
							reset();
						}
					}
				}
				if(tipoJuego == 1) {	//EN SALTOS A LA IZQ SOLO PODEMOS FUSIONAR LAS CARTAS DE UNA EN UNA
					
					Point posi = getLocationOnScreen();
					movDeshacerPo.add(posi);
					boolean anhade = false;
					if(tempPileJ != null) {
						
						Point mousePos = e.getLocationOnScreen();
						boolean match = false;
						PilaSaltos pilaPos = jump.getPila(jump.tamPilas() - 1);
						PilaSaltos pila1Pos = null, pila3Pos = null;
						
						if(jump.tamPilas() >= 2) {
							pila1Pos = jump.getPila(jump.tamPilas() - 2);
						}	
						if(jump.tamPilas() >= 4) {
							pila3Pos = jump.getPila(jump.tamPilas() - 4);
						}
						
						//Comprueba si la pila puede fusionarse con la pila sobre la que se tira.
						ArrayList<PilaSaltos> droppableJump = new ArrayList<PilaSaltos>(jump.pila);
						
						for(PilaSaltos p: droppableJump) {

							try {
								Point pilePos = p.getLocationOnScreen();
								Rectangle r = p.getBounds();
								r.x = pilePos.x;
								r.y = pilePos.y;
							
								if(jump.tamPilas() >= 4) {
									
									if(r.contains(mousePos) && p.movimientoValido(tempPileJ, pila3Pos)) {
										p.merge(tempPileJ);
										match = true;
										break;
									}
									else {
										if(r.contains(mousePos) && (p.movimientoValido(tempPileJ, pila1Pos) || pila1Pos.isEmpty())) {
											p.merge(tempPileJ);
											match = true;
											break;
										}
										else {
											if(r.contains(mousePos) && pilaPos.isEmpty()) {
												p.merge(tempPileJ);
												match = true;
												//jump.anhadePila();
												anhade = true;
												break;
											}
										}
									}
								}
								else {
									if(jump.tamPilas() >= 2) {
										
										if(r.contains(mousePos) && (p.movimientoValido(tempPileJ, pila1Pos) || pila1Pos.isEmpty())) {
											p.merge(tempPileJ);
											match = true;
											break;
										}
										else {
											if(r.contains(mousePos) && pilaPos.isEmpty()) {
												p.merge(tempPileJ);
												match = true;
												//jump.anhadePila();
												anhade = true;
												break;
											}
										}
									}
									else {
										if(r.contains(mousePos) && pilaPos.isEmpty()) {
											p.merge(tempPileJ);
											match = true;
											//jump.anhadePila();
											anhade = true;
											break;
										}
									}
								}
							}catch(Exception e1) {
									
							}
						}
						if((anhade == true) || !(pilaPos.isEmpty())) {
							jump.anhadePila();
							++numPilas;
							
							jump.setupJump(numPilas);
							PilaSaltos pi = jump.getPila(jump.tamPilas() - 1);
							columnas.add(pi);
							
							columnas.add(jump.drawPila);
							topColumnas.add(jump.drawPila);
							topColumnas.add(jump.getPila);
							
							for(PilaSaltos po : jump.finalPila) {
								topColumnas.add(po);
							}
	
							areaJuego.add(topColumnas);
							areaJuego.add(columnas);
							
							add(areaJuego);
							lp.add(tempPileJ);
							setVisible(true);
							
							validate();
							
						}
						
						//Retrocede si no se encuentra una fusión
						if(!match) tempPileJ.parent.merge(tempPileJ);
						
						lp.remove(tempPileJ);					
						tempPileJ = null;
	
						repaint();
						
						if(jump.checkWin()) {
							JOptionPane.showMessageDialog(this, "¡Has ganado esta partida, Felicidades!");
							winSolSaltos++;
							reset();
						}
					}
				}	
		}
		
		
		/**
		 * Estos 2 métodos gestionan los movimientos del raton, funcionan cuando muevo el raton.
		 **/
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		
}
