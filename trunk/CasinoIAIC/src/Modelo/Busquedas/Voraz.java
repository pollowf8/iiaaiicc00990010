package Modelo.Busquedas;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;
import Modelo.Juegos.*;

public class Voraz implements Busqueda{

	@Override
	public Juego resuelve(Juego inicial){
		if (inicial.getValorHeur()==-1)
			return inicial;
		boolean fin=false;
		int generados=1;
		int expandidos=0;
		List<Juego> listaCerrados=new Vector<Juego>(0,1);
		PriorityQueue<Juego> colaAbiertos=new PriorityQueue<Juego>(1,new ComparatorVoraz());
		colaAbiertos.offer(inicial);
		Juego juego=null;
		while (!fin){
			juego=colaAbiertos.poll();
			if (juego.isGoal()){
				fin=true;
				String camino=juego.getCamino()+"Nodos generados:"+generados+" ; Nodos expandidos:"+expandidos+" ; Coste: "+juego.getCoste()+
								" ; Profundidad:"+juego.getProfundidad();
				juego.setCamino(camino);
			}
			else{
				// como no es estado final, se cierra, ya no se va a mirar más
				listaCerrados.add(juego);
				// expandir un nuevo nodo
				expandidos++;
				// sucesores del nodo que se acaba de cerrar
				Vector<Juego> sucesores=juego.expandir();
				// si uno de los sucesores ya aparece en la lista de nodos cerrados, no se añade a la cola de abiertos, así se evitan ciclos
				for (int i=0;i<sucesores.size();i++){
					if (!listaCerrados.contains(sucesores.elementAt(i))){
						colaAbiertos.offer(sucesores.elementAt(i));
						generados++;
					}
				}
			}
		}
		return juego;
	}
	
	public static void main(String[] args){
		Puzzle8 puzzle=new Puzzle8();
		Voraz busqueda=new Voraz();
		Puzzle8 solucion=(Puzzle8)busqueda.resuelve(puzzle);
		System.out.print(solucion.getCamino());
		System.out.print("\nSolución: "+solucion.toString());
	}
}