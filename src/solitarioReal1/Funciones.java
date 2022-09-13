package solitarioReal1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import solitarioReal1.SolitarioClasico;

public class Funciones {

		
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		SolitarioClasico solCla = new SolitarioClasico();
		ArrayList<Pila> allPiles = solCla.getAllPilas();
		int pileNumber = solCla.getPileNumber();
		Pila drawPila = solCla.getDrawPila();
		Pila getPila = solCla.getgetPila();
		ArrayList<Pila> pila = solCla.getPilas();
		ArrayList<Pila> finalPila = solCla.getFinalPila();
	
	

	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * Función que carga el juego desde un archivo xml.
		 * 		Recibe: el nombre del archivo.
		 * 		Devuelve: nada.
		 **/
		public void load(String nombre) {
			
			 	BufferedReader objReader = null; 
		        try {  
		            String strCurrentLine;   
		            objReader = new BufferedReader(new FileReader(nombre));   
		            while (! ((strCurrentLine = objReader.readLine()) == null)) {    
		                System.out.println(strCurrentLine);  
		            }  
		        } catch (IOException e) {   
		            e.printStackTrace();  
		        }
		        
	
		}
		
		/**
		 *  Funcion Guardar.
		 * 		Recibe: un string con la ruta, un int con el tipo de juego.
		 * 		Devuelve: nada.
		 */
		public void save(String ruta, int tipJueg) {
			
				BufferedWriter bw;	
				File archivo = new File(ruta);	
				String newLine = System.getProperty( "line.separator" );	
			
				if(archivo.exists()) {
					
					try {
						
						FileWriter fw = new FileWriter(archivo);
						bw = new BufferedWriter(fw);

						if(tipJueg == 1) {
									
							bw.write("Solitario Saltos.");
							
						}
						else {
							
							if(tipJueg == 2) {

								bw.write("Solitario Clásico." + newLine);
								solCla.saveCla(bw);	
								
							}
						}	
						bw.close();
						
					} catch (IOException e) {
						
						System.out.println(e);
					}		
	
		        } else {
		        	
		        	System.out.println("No Existe");
		        }
		}
		
		
		/**
		 * @throws IOException 
		 * 
		 **/
		
		public void escribFich(String ruta, int[] datos) {
			
			BufferedWriter bw;		
			File fichero = new File(ruta);
			String newLine = System.getProperty( "line.separator" );	
			
			if(fichero.exists()) {
				
				try {	
					FileWriter fw = new FileWriter(fichero);
					bw = new BufferedWriter(fw);
					bw.write("Solitario Saltos." + newLine);
					bw.write("\tIntentos realizados: " + datos[0] + newLine);
					bw.write("\tIntentos realizados con éxito: " + datos[1] + newLine);
						
					bw.write("Solitario Clásico." + newLine);
					bw.write("\tIntentos realizados: " + datos[2] + newLine);
					bw.write("\tIntentos realizados con éxito: " + datos[3] + newLine);			
					bw.close();
					
				}catch(Exception e) {
					System.out.println(e);
				}
			}  
			System.out.println("Estadistica done");
		}
}
