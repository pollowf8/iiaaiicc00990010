/**
 * 
 */
package Modelo;

import java.util.ArrayList;
import java.util.Random;

import Modelo.Juegos.Juego;

/**
 * @author jga
 *
 */
public class Casino {

	private int vidas;
	private int zonaActual;
	private ArrayList<Juego> juegos;
	private ArrayList<Zona> zonas;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Casino();
	}
	
	public Casino(){
		this.vidas=29000;
		this.zonaActual=0;
		this.juegos=new ArrayList<Juego>();
		/*
		for (int i=0; i<10; i++){
			this.juegos.add(new Juego());
		}*/
		// TODO
		// Crear los juegos y añadirlos
		zonas=new ArrayList<Zona>();
		this.creaZonas();
	}
	
	public boolean jugar(){
		return true;
	}
	
	private void creaZonas(){
		int zonaPadre=0;
		zonas.add(0, new Zona(0,0,0,0,0));
		while (zonas.size()<20){
			Random r=new Random();
			int numhijos=r.nextInt(4)+2;
			int i=0;
			while (i<numhijos && zonas.size()<20){
				int numJuego=r.nextInt(juegos.size()-1);
				Juego aux=juegos.get(numJuego);
				while (aux.completo()){
					numJuego=r.nextInt(juegos.size()-1);
					aux=juegos.get(numJuego);
				}
				int numBus=aux.getNumBus();
				zonas.add(zonas.size(), new Zona(numJuego,numBus,zonaPadre,0,0));
				zonas.get(zonaPadre).addHijo(zonas.size()-1);
				i++;
			}
			zonaPadre++;
		}
	}

}
