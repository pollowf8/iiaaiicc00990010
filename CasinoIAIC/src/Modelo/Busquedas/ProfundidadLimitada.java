package Modelo.Busquedas;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import Modelo.Juegos.*;

/**
 * Búsqueda en profundidad limitada. Es una búsqueda primero en profundidad, pero limita el número de niveles que se pueden expandir. Incluye
 * control de ciclos, y también manejadores de excepciones (por si se vacía la pila y no se ha encontrado aún la solución) y un manejador de 
 * error (por si la memoria se llena antes de llegar al objetivo).
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 */
public class ProfundidadLimitada implements Busqueda{
	
	private int profundidad;
	private final int DEFAULTprof=15;
	
	public ProfundidadLimitada(int p){
		profundidad=p;
	}

	public ProfundidadLimitada() {
		profundidad=this.DEFAULTprof;
	}

	public String toString() {
		return "Profundidad limitada";
	}
	
	@Override
	public Juego resuelve(Juego inicial){
		boolean fin=false;
		int generados=1;
		int expandidos=0;
		List<Juego> listaCerrados=new Vector<Juego>(0,1);
		Stack<Juego> pilaAbiertos=new Stack<Juego>();
		pilaAbiertos.push(inicial);
		Juego juego=null;
		// se hace try&catch porque ésta búsqueda puede no acabar por falta de memoria. imposible por los juegos que se van a usar? A REVISAR
		try{
			// el while incluye la condición de no encontrarse la pila vacía, que significa que ya no hay más nodos abiertos que explorar, por lo que se ha
			// recorrido el árbol completo y no se ha llegado a la solución. Sólo pasa con ésta búsqueda, como se limita la profundidad, puede que la solución esté
			// más hundida, por lo que nunca se llega a explorar.
			while (!fin && !pilaAbiertos.isEmpty()){
				juego=pilaAbiertos.pop();
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
					// por ser una pila se meten al revés, para recorrerlos en el orden en que se aplican los operadores. Si un sucesor ya estaba en la
					// lista de cerrados, no se introduce en la pila, para evitar ciclos/ramas infinitas. Además, si la profundidad del nodo que se acaba de
					// cerrar es mayor que la profundidad límite, tampoco se introducen sus sucesores en la pila.
					for (int i=sucesores.size();i>0;i--){
						if (!listaCerrados.contains(sucesores.elementAt(i-1))&&juego.getProfundidad()<profundidad){
							pilaAbiertos.push(sucesores.elementAt(i-1));
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
		Juego inicial=new Hanoi();
		ProfundidadLimitada busqueda=new ProfundidadLimitada(15);
		Juego solucion=(Hanoi)busqueda.resuelve(inicial);
		System.out.print("Búsqueda en profundidad limitada a 15:\nEstado inicial: "+inicial.toString());
		if (solucion.isGoal()){
			System.out.print(solucion.getCamino());
			System.out.print("\nSolución: "+solucion.toString());
		}
		else
			System.out.print("No se encontró ninguna solución");
	}
	
}