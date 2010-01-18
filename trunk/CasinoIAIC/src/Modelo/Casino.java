/**
 * 
 */
package Modelo;

import java.util.ArrayList;
import java.util.Random;

import Modelo.Busquedas.*;
import Modelo.Juegos.*;

/**
 * @author jga
 *
 */
public class Casino {

	private int vidas;
	private int zonaActual;
	private ArrayList<Zona> zonas;
	private Juego juegos[];
	private Busqueda busquedas[];
	
	/**
	 * Número máximo de búsquedas para resolver el juego
	 */
	public static final int MAXbusquedas=8;
	
	/**
	 * Número máximo de juegos disponibles para resolver
	 */
	public static final int MAXJuegos=5;
	
	/**
	 * Número máximo de zonas del casino
	 */
	private final int MAXZonas=Casino.MAXJuegos*Casino.MAXbusquedas;
	
	/**
	 * Número de salidas
	 */
	private final int MAXSalidas=3;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Casino test=new Casino();
		System.out.println("Fin de la creación del casino.");
	}
	
	public Casino(){
		this.vidas=29000;
		this.zonaActual=-1;
		this.initJuegos();
		this.initBusquedas();
		this.initZonas();
	}
	
	private void initBusquedas() {
		this.busquedas=new Busqueda[Casino.MAXbusquedas];
		for (int i=0; i<Casino.MAXbusquedas; i++){
			switch (i){
			case 0:
				this.busquedas[i]=new AEstrella();
				break;
			case 1:
				this.busquedas[i]=new CosteUniforme();
				break;
			case 2:
				this.busquedas[i]=new EscaladaMaximaPendiente();
				break;
			case 3: 
				this.busquedas[i]=new PrimeroAnchura();
				break;
			case 4: 
				this.busquedas[i]=new PrimeroProfundidad();
				break;
			case 5: 
				this.busquedas[i]=new ProfundidadIterativa();
				break;
			case 6: 
				this.busquedas[i]=new ProfundidadLimitada();
				break;
			case 7: 
				this.busquedas[i]=new Voraz();
				break;
			}
		}
	}

	private void initJuegos() {
		this.juegos=new Juego[Casino.MAXJuegos];
		for (int i=0; i<Casino.MAXJuegos; i++){
			switch (i){
			case 0:
				this.juegos[i]=new Garrafas();
				break;
			case 1:
				this.juegos[i]=new Hanoi();
				break;
			case 2:
				this.juegos[i]=new LoboCabraCol();
				break;
			case 3: 
				this.juegos[i]=new Misioneros();
				break;
			case 4: 
				this.juegos[i]=new Puzzle8();
				break;
			}
		}
	}

	public boolean jugar(){
		// TODO jugar
		return true;
	}
	
	private void initZonas(){
		zonas=new ArrayList<Zona>(Casino.MAXJuegos*Casino.MAXbusquedas);
		for (int i=0; i<this.MAXZonas; i++){
			Random r=new Random();
			int numJuego=r.nextInt(Casino.MAXJuegos);
			Juego aux=juegos[numJuego];
			while (aux.completo()){
				numJuego=r.nextInt(Casino.MAXJuegos);
				aux=juegos[numJuego];
			}
			int numBus=aux.getNumBus();
			this.zonas.add(new Zona(numJuego,numBus,0,0));
			if (i>=this.MAXZonas-this.MAXSalidas) this.zonas.get(i).setFin(true);
		}
		this.generaCaminos();
	}

	private void generaCaminos() {
		Random r=new Random();
		for (int i=0; i<(int) (this.MAXZonas*0.2); i++){
			for (int j=0; j<4; j++){
				int indice=r.nextInt(this.MAXZonas-i-1);
				this.zonas.get(i).addHijo(indice+i+1);
				this.zonas.get(indice+i+1).addPadre(i);
			}
		}
		for (int i=(int) (this.MAXZonas*0.2); i<this.MAXZonas-this.MAXSalidas; i++){
			for (int j=0; j<3; j++){
				int indice=r.nextInt(this.MAXZonas-i-1);
				this.zonas.get(i).addHijo(indice+i+1);
				this.zonas.get(indice+i+1).addPadre(i);
			}
			int indice=r.nextInt(i);
			this.zonas.get(i).addHijo(indice);
			this.zonas.get(indice).addPadre(i);
		}
		for (int i=0; i<this.MAXSalidas; i++){
			if (this.zonas.get(this.MAXZonas-this.MAXSalidas+i).getNumPadres()==0){
				int indice=r.nextInt((int) (this.MAXZonas*0.1))+1;
				this.zonas.get(this.MAXZonas-this.MAXSalidas-indice).addHijo(this.MAXZonas-this.MAXSalidas+i);
				this.zonas.get(this.MAXZonas-this.MAXSalidas+i).addPadre(this.MAXZonas-this.MAXSalidas-indice);
			}
		}
		
	}

}
