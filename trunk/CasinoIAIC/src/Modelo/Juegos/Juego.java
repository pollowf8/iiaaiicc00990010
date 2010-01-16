package Modelo.Juegos;

import java.util.Vector;

/**
 * Clase abstracta Juego. Proporciona la estructura común de todos los juegos, que después implementarán los métodos abstractos según
 * los estados y operadores de cada uno. Cada instancia de ésta clase, o de las que heredan de ella, representa un único estado de juego.
 * @author Pablo Acevedo
 *
 */
public abstract class Juego{
	/**
	 * valor heurístico del juego. -1 si el juego no soporta heurísticas
	 */
	protected int valorHeur;
	/**
	 * coste acumulado desde el estado inicial
	 */
	protected int coste;
	/**
	 * profundidad del nodo en el árbol de búsqueda
	 */
	protected int profundidad;
	/**
	 * operadores que se han aplicado desde el estado inicial
	 */
	protected String camino;
	
	/**
	 * @return valor heurístico del estado
	 */
	public int getValorHeur(){
		return valorHeur;
	}
	
	/**
	 * @return coste acumulado hasta llegar a este estado
	 */
	public int getCoste(){
		return coste;
	}
	
	/**
	 * @return profundidad del estado en el árbol de búsqueda
	 */
	public int getProfundidad(){
		return profundidad;
	}
	
	/**
	 * @return camino desde el inicio hasta este estado 
	 */
	public String getCamino(){
		return camino;
	}
	
	/**
	 * @param s string que sobreescribe el camino que habia antes
	 */
	public void setCamino(String s){
		camino=s;
	}
	
	/**
	 * @return vector con todos los posibles sucesores del estado
	 */
	public abstract Vector expandir();
	
	/**
	 * cambiar el estado del juego al objetivo
	 */
	public abstract void setGoal();
	
	public abstract boolean equals(Object o);

	public abstract String toString();
}