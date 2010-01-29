package main;

import Swing.Swing;
import Controlador.Controlador;

/**
 * 
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class Principal {
	
		public static void main(String[] args) 
		{
			try{
				Swing interfaz=new Swing();
				Controlador.getInstance();
				while (true){
					dormir(100);
					while (interfaz.nuevaPartida()){
						Controlador.getInstance().creaCasino();
						interfaz.creaVentana();					
					}
				}
			}
			catch(NullPointerException e){
				System.out.println("Error fatal");
			}
		}
		
		private static void dormir(int i){
			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {}
		}
}