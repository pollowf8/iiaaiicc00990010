/**
 * 
 */
package Modelo;

/**
 * @author jga
 *
 */
public class Juego {
	private int numBusquedas;
	private boolean completo;
	
	public Juego(){
		this.numBusquedas=6;
		this.completo=false;
	}
	
	public boolean completo(){
		return numBusquedas==0;
	}
	
	public int getNumBus(){
		int aux=numBusquedas;
		numBusquedas--;
		return aux;
	}
}
