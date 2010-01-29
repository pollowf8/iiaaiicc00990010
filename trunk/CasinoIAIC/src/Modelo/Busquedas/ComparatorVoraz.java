package Modelo.Busquedas;

import java.util.Comparator;
import Modelo.Juegos.Juego;

/**
 * 
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class ComparatorVoraz implements Comparator<Juego>{

	@Override
	public int compare(Juego o1, Juego o2) {
		if (o1.getValorHeur()<o2.getValorHeur())
			return -1;
		if (o1.getValorHeur()>o2.getValorHeur())
			return 1;
		return 0;
	}
	
}