package Modelo.Busquedas;

import java.util.Comparator;
import Modelo.Juegos.Juego;

public class ComparatorCosteUniforme implements Comparator<Juego>{

	@Override
	public int compare(Juego o1, Juego o2) {
		if (o1.getCoste()>o2.getCoste())
			return 1;
		if (o1.getCoste()<o2.getCoste())
			return -1;
		return 0;
	}
	
}