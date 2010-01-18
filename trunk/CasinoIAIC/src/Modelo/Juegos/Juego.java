package Modelo.Juegos;

import java.util.Random;
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
	protected double valorHeur;
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
	 * Número máximo de búsquedas para resolver el juego
	 */
	private final int MAXbusquedas=8;
	/**
	 * Número de búsquedas asignadas aleatoriamente.
	 */
	private int NUMbusquedas;
	/**
	 * Indica cuales de las 8 búsquedas han sido asignadas
	 */
	private boolean usadas[];
	
	/****************************************************************************/
	
	/**
	 * Constructora. Inicializa los atributos.
	 */
	public Juego(){
		this.usadas=new boolean[this.MAXbusquedas];
		this.NUMbusquedas=0;
		for (int i=0; i<this.MAXbusquedas; i++){
			this.usadas[i]=false;
		}
	}
	
	/**
	 * Indica si están asignadas todas las busquedas.
	 * @return true si no quedan busquedas a asignar.
	 */
	public boolean completo(){
		return this.NUMbusquedas==0;
	}
	
	/**
	 * Asigna una busqueda aleatoriamente
	 * @return integer de 0 a 7 que indica la busqueda asignada. -1 en caso de 
	 * estar todas asignadas.
	 */
	public int getNumBus(){
		if (this.completo()) return -1;
		/*
		 * Generamos aleatoriamente un numero entre 0 y 7
		 */
		Random r=new Random();
		int indice=r.nextInt(this.MAXbusquedas);
		/*
		 * Seguimos generando numeros hasta encontrar una busqueda libre
		 */
		while (this.usadas[indice]==true){
			indice=r.nextInt(this.MAXbusquedas);
		}
		return indice;
	}
	
	/**
	 * @return valor heurístico del estado
	 */
	public double getValorHeur(){
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
	public abstract Vector<Juego> expandir();
	
	/**
	 * Comprueba si el estado actual es un objetivo
	 * @return true si es un estado final
	 */
	public abstract boolean isGoal();
	
	public abstract boolean equals(Object o);

	public abstract String toString();
}