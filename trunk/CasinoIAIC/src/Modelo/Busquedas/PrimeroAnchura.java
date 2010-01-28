package Modelo.Busquedas;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import Modelo.Juegos.*;

/**
 * Búsqueda primero en anchura. Desde un estado inicial va expandiendo nodos y recorriendolos con una cola, de forma
 * que se busca la solucion por niveles. Incluye control de ciclos.
 * @author Pablo Acevedo
 * 
 */
public class PrimeroAnchura implements Busqueda{

	public String toString() {
		return "Primero en anchura";
	}
	
	@Override
	public Juego resuelve(Juego inicial){
		boolean fin=false;
		int generados=1;
		int expandidos=0;
		List<Juego> listaCerrados=new Vector<Juego>(0,1);
		Queue<Juego> colaAbiertos=new LinkedList<Juego>();
		colaAbiertos.offer(inicial);
		Juego juego=null;
		try{
			while (!fin){
				juego=colaAbiertos.poll();
				if (juego.isGoal()){
					// Si el juego es igual que el estado final, se guarda en su camino toda la información de la búsqueda para mostrarla después
					fin=true;
					String camino=juego.getCamino()+"Nodos generados:"+generados+" ; Nodos expandidos:"+expandidos+" ; Coste: "+juego.getCoste()+
									" ; Profundidad:"+juego.getProfundidad();
					juego.setCamino(camino);
					juego.setNodos(expandidos);
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
		catch (Error e){
			return juego;
		}
	}
	
	/**
	 * Main para probar la búsqueda
	 * @param args, no se usa
	 */
	public static void main(String[] args){
		Juego inicial=new Mono();
		PrimeroAnchura busqueda=new PrimeroAnchura();
		System.out.print("Búsqueda en anchura Garrafas:\nEstado inicial: "+inicial.toString());
		Juego solucion=(Mono)busqueda.resuelve(inicial);
		System.out.print(solucion.getCamino());
		System.out.println("\nSolución: "+solucion.toString());
	}
	
}