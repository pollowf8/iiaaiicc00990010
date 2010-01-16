package Modelo.Busquedas;

import Modelo.Juegos.Juego;

/**
 * Interfaz que da un método de resolución general para cualquier búsqueda. Cada tipo de búsqueda implementará el método según su estrategia.
 * @author Pablo Acevedo
 *
 */
public interface Busqueda{
	/**
	 * Resuelve un juego con una busqueda
	 * @param juego inicial
	 * @param juego objetivo
	 * @return juego al que se ha llegado
	 */
	public Juego resuelve(Juego inicial,Juego goal);
}

