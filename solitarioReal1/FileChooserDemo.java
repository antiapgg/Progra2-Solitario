package solitarioReal1;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;
 
public class FileChooserDemo extends JPanel {
	
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //											DECLARACION DE VARIABLES											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    JTextArea log;
    JFileChooser fc;
  
    
	
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //													CONSTRUCTOR													//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    public FileChooserDemo() {
    	
        super(new BorderLayout());
 
        fc = new JFileChooser();
    }
    
    

	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 //														MÉTODOS	 	 											//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
	    /**
	     * Función que muestra la pantalla de cargar
	     * 		Recibe: nada.
	     * 		Devuelve: nada.
	     **/
	    public void load() {
	    	
	    	fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        int returnVal = fc.showOpenDialog(FileChooserDemo.this);
	        
	        if (returnVal == JFileChooser.APPROVE_OPTION) {       	
	            File file = fc.getSelectedFile();
	            
	            JOptionPane.showMessageDialog(this,"Abriendo: " + file.getName() + ".");
	            
	            Funciones funcion = new Funciones();
	            funcion.load(file.getName());
	        } 
	        else {
	        	JOptionPane.showMessageDialog(this,"El comando cargar fue cancelado por el usuario.");
	        }
	    }
	 
	    /**
	     * Función que muestra la pantalla de guardar como.
	     * 		Recibe: nada.
	     * 		Devuelve: un String con el nombre.
	     **/
	    public String saveAs(int tipJueg) {
	    	
	    	fc.setApproveButtonText("Guardar como");
	    	fc.showSaveDialog(null);
	
	    	String ruta = fc.getSelectedFile()+".txt";
	        
	        JOptionPane.showMessageDialog(this,"Guardando: " + ruta + ".");
	        
	        Funciones funcion = new Funciones();
	        File f = new File(ruta);
	        
	        try {
				if (f.createNewFile()) {

				    funcion.save(ruta, tipJueg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            
	       return ruta;
	    }
	    
	       
	    /**
	     * Función que muestra la pantalla de guardar como.
	     * 		Recibe: nada.
	     * 		Devuelve: un String con el nombre.
	     * @throws IOException 
	     **/
	    public String saveFich(int[] datos) throws IOException {
	    	
	    	fc.setApproveButtonText("Guardar como");
	    	fc.showSaveDialog(null);
	    	
	    	String ruta = fc.getSelectedFile()+".txt";
	    	
	        JOptionPane.showMessageDialog(this,"Guardando: " + ruta + " .");
	        
	        Funciones funcion = new Funciones();
	        
	        File f = new File(ruta);
	        
	        try {
				if (f.createNewFile()) {

				    funcion.escribFich(ruta, datos);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return ruta;
	    }
}
