package Modelo.Busquedas;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import Modelo.Juegos.*;

/**
 * Búsqueda primero en profundidad. Desde el estado inicial, se van expandiendo sucesores, y se recorren con una pila, de forma que se explora el primer nodo
 * recién expandido antes de seguir con los demás. Al utilizar una pila se hace backtracking si se llega a una rama cerrada que no tiene solución. Incluye
 * control de ciclos, y manejador de error, por si la memoria se llena antes de llegar al objetivo.
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 */
public class PrimeroProfundidad implements Busqueda{

	public String toString() {
		return "Primero en profundidad";
	}
	
	@Override
	public Juego resuelve(Juego inicial) {
		boolean fin=false;
		int generados=1;
		int expandidos=0;
		List<Juego> listaCerrados=new Vector<Juego>(0,1);
		Stack<Juego> pilaAbiertos=new Stack<Juego>();
		pilaAbiertos.push(inicial);
		Juego juego=null;
		// se hace try&catch porque ésta búsqueda puede no acabar por falta de memoria. depende del juego que uses, puzzle8 acaba con la memoria, garrafas no
		try{
			while (!fin){
				juego=pilaAbiertos.pop();
				if (juego.isGoal()){
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
					// lista de cerrados, no se introduce en la pila, para evitar ciclos/ramas infinitas.
					for (int i=sucesores.size();i>0;i--){
						if (!listaCerrados.contains(sucesores.elementAt(i-1))){
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
		Juego inicial=new LoboCabraCol();
		PrimeroProfundidad busqueda=new PrimeroProfundidad();
		Juego solucion=(LoboCabraCol)busqueda.resuelve(inicial);
		System.out.print("Búsqueda primero en profundidad Lobo, cabra y col:\nEstado inicial: "+inicial.toString());
		if (solucion.isGoal()){
			System.out.print(solucion.getCamino());
			System.out.print("\nSolución: "+solucion.toString());
		}
		else
			System.out.print("No se encontró ninguna solución");
	}
}