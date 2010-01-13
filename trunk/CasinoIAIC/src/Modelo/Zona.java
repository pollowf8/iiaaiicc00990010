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
	private int zonaPadre;
	private ArrayList<Integer> hijos;
	
	public Zona(int juego, int busqueda, int padre, int apuesta, int premio){
		this.juego=juego;
		this.busqueda=busqueda;
		this.zonaPadre=padre;
		this.apuesta=apuesta;
		this.premio=premio;
		this.hijos=new ArrayList<Integer>();
	}
	
	public void addHijo(int hijo){
		hijos.add(hijo);
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

	public int getZonaPadre() {
		return zonaPadre;
	}

	public void setZonaPadre(int zonaPadre) {
		this.zonaPadre = zonaPadre;
	}

	public int getNumHijos() {
		return hijos.size();
	}
	
	public int getHijo(int indice){
		try{
		return this.hijos.get(indice);
		} catch (ArrayIndexOutOfBoundsException e){
			return 101;
			}
	}
	
}
