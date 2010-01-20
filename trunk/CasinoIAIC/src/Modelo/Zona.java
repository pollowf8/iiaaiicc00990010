/**
 * 
 */
package Modelo;

import java.util.ArrayList;

/**
 * @author jga
 *
 */
public class Zona {
	private int juego;
	private int busqueda;
	private int apuesta;
	private int premio;
	private ArrayList<Integer> hijos;
	private boolean fin;
	
	/**
	 * @return the fin
	 */
	public boolean isFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(boolean fin) {
		this.fin = fin;
	}
	
	public Zona(int juego, int busqueda, int apuesta, int premio){
		this.juego=juego;
		this.busqueda=busqueda;
		this.apuesta=apuesta;
		this.premio=premio;
		this.hijos=new ArrayList<Integer>(5);
	}

	public int getJuego() {
		return juego;
	}

	public void setJuego(int juego) {
		this.juego = juego;
	}

	public int getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(int busqueda) {
		this.busqueda = busqueda;
	}

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public void addHijo(int hijo){
		hijos.add(hijo);
	}
	
	public int getNumHijos() {
		return hijos.size();
	}
	
	public int getHijo(int indice){
		try{
		return this.hijos.get(indice);
		} catch (ArrayIndexOutOfBoundsException e){
			return -1;
			}
	}

	/**
	 * @return the hijos
	 */
	public ArrayList<Integer> getHijos() {
		return hijos;
	}	
}
