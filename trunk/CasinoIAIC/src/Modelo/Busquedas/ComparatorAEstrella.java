package Modelo.Busquedas;

import java.util.Comparator;
import Modelo.Juegos.*;

/**
 * 
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class ComparatorAEstrella implements Comparator<Juego>{

	@Override
	public int compare(Juego o1, Juego o2) {
		if((o1.getCoste()+o1.getValorHeur())<(o2.getCoste()+o2.getValorHeur()))
			return -1;
		if ((o1.getCoste()+o1.getValorHeur())>(o2.getCoste()+o2.getValorHeur()))
			return 1;
		return 0;
	}
	
}