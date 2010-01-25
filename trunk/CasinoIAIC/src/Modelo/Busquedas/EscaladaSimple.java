package Modelo.Busquedas;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import Modelo.Juegos.*;

public class EscaladaSimple implements Busqueda{

	public String toString() {
		return "Escalada simple";
	}
	
	@Override
	public Juego resuelve(Juego inicial) {
		if (inicial.getValorHeur()==-1 || inicial.isGoal())
			return inicial;
		boolean fin=false;
		int generados=1;
		int expandidos=0;
		List<Juego> listaCerrados=new Vector<Juego>(0,1);
		Queue<Juego> colaAbiertos=new LinkedList<Juego>();
		colaAbiertos.offer(inicial);
		Juego juego=null;
		while (!fin && !colaAbiertos.isEmpty()){
			juego=colaAbiertos.poll();
			if (juego.isGoal()){
				fin=true;
				String camino=juego.getCamino()+"Nodos generados:"+generados+" ; Nodos expandidos:"+expandidos+" ; Coste: "+juego.getCoste()+
								" ; Profundidad:"+juego.getProfundidad();
				juego.setCamino(camino);
			}
			else{
				listaCerrados.add(juego);
				expandidos++;
				Vector<Juego> sucesores=juego.expandir();
				int i=0;
				while (i<sucesores.size() && colaAbiertos.isEmpty()){
					if (!listaCerrados.contains(sucesores.elementAt(i))){
						if (sucesores.elementAt(i).getValorHeur()<juego.getValorHeur()){
							generados++;
							colaAbiertos.offer(sucesores.elementAt(i));
						}
					}
					i++;
				}
			}
		}
		return juego;
	}
	
	public static void main(String[] args){
		Juego inicial=new Puzzle8();
		EscaladaSimple busqueda=new EscaladaSimple();
		Juego solucion=(Puzzle8)busqueda.resuelve(inicial);
		System.out.println("Búsqueda por escalada simple, Puzzle8\n");
		if (solucion.isGoal()){
			System.out.println(solucion.getCamino());
			System.out.println(solucion.toString());
		}
		else
			System.out.println("No se encontró solución");
	}
}