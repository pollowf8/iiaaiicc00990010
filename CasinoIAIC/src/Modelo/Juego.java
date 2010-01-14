/**
 * 
 */
package Modelo;

import java.util.Random;

/**
 * @author jga
 *
 */
public abstract class Juego {
	private final int MAXbusquedas=8;
	private int NUMbusquedas;
	private boolean usadas[];
	
	public Juego(){
		this.usadas=new boolean[this.MAXbusquedas];
		this.NUMbusquedas=0;
		for (int i=0; i<this.MAXbusquedas; i++){
			this.usadas[i]=false;
		}
	}
	
	public boolean completo(){
		return this.NUMbusquedas==0;
	}
	
	public int getNumBus(){
		if (this.completo()) return -1;
		Random r=new Random();
		int indice=r.nextInt(this.MAXbusquedas);
		while (this.usadas[indice]==true){
			indice=r.nextInt(this.MAXbusquedas);
		}
		return indice;
	}
	
	public abstract boolean lanzaJuego(int busqueda);
}
