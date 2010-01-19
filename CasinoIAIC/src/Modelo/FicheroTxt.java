package Modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FicheroTxt {

	private String texto;
	
	
	public FicheroTxt(){
		texto="";
	}
	
	public void escribeFichero(String texto2){		
		if (texto.isEmpty())
			texto=texto2;
		else{
			texto=texto+"\r\n"+texto2;
		}		
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
		    String path = "Log.txt";  
		    fichero = new FileWriter(path, false);
		    pw = new PrintWriter(fichero);
		    pw.println(texto);
        } catch (Exception e) {e.printStackTrace();}
		        finally {
		           if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e) {}
		           try {
		           } catch (Exception e2) {e2.printStackTrace();}
		        }
	}

	@SuppressWarnings("static-access")
	public void open() {
		//JFRAME
		JFrame log=new JFrame("LOG CASINO MICROMUNDOS FANTASMA");
		log.setSize(600,450);
		log.setLayout(new BorderLayout());
		log.setDefaultCloseOperation(log.DISPOSE_ON_CLOSE);
		//JTEXTAREA
		JTextArea text= new JTextArea();
		text.setEditable(false);
		text.setBackground(Color.WHITE);
		text.setText(this.texto);
		//SCROLL
		JScrollPane scroll = new JScrollPane(text);
		
		log.add(scroll);
		log.setVisible(true);
	}		        
	
	
}
