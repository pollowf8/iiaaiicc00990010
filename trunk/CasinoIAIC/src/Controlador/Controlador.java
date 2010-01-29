package Controlador;

import Modelo.Casino;
import Swing.Panel;

/**
 * 
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class Controlador {

	private static Controlador instance = null;
	private Casino casino;
	
	public static Controlador getInstance() {
		//Creo el objeto localmente si es la primera llamada
		if(instance == null) {
			instance = new Controlador();
		}
		return instance;
	}


	public void ejecutaOpcion(int opcion) {
		if (opcion==1)
			casino.jugar();
		else if (opcion==2)
			casino.mostrarLog();
	}


	public void creaCasino() {
		casino=new Casino();
	}


	public void añadirObs(Panel panel) {
		casino.addObserver(panel);		
	}


	
}
