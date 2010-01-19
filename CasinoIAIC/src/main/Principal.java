package main;

import Swing.Swing;
import Controlador.Controlador;

public class Principal {
	
		public static void main(String[] args) 
		{
			try{
				Controlador.getInstance();
				Controlador.getInstance().creaCasino();
				@SuppressWarnings("unused")
				Swing interfaz=new Swing();
			}
			catch(NullPointerException e){
				System.out.println("Error fatal");
			}
		}	
}