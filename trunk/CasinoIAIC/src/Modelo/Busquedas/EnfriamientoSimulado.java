package Modelo.Busquedas;

import java.util.Random;
import java.util.Vector;
import Modelo.Juegos.*;

public class EnfriamientoSimulado implements Busqueda{

	public String toString() {
		return "Enfriamiento simulado";
	}
	
	@Override
	public Juego resuelve(Juego inicial) {
		if (inicial.getValorHeur()==-1 || inicial.isGoal())
			return inicial;
		Random rand=new Random();
		int r;
		int generados=1;
		int expandidos=1;
		Juego actual=inicial;
		Juego mejorHastaAhora=actual;
		double T=1;
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		sucesores=inicial.expandir();
		generados+=sucesores.size();
		while (!sucesores.isEmpty() && T>0){
			r=rand.nextInt(sucesores.size());
			Juego nuevoEstado=sucesores.elementAt(r);
			double e=nuevoEstado.getValorHeur()-actual.getValorHeur();
			if (nuevoEstado.isGoal()){
				String camino=nuevoEstado.getCamino()+"Nodos generados:"+generados+" ; Nodos expandidos:"+expandidos+" ; Coste: "+nuevoEstado.getCoste()+
				" ; Profundidad:"+nuevoEstado.getProfundidad();
				nuevoEstado.setCamino(camino);
				nuevoEstado.setNodos(expandidos);
				return nuevoEstado;
			}
			else{
				if (nuevoEstado.getValorHeur()<actual.getValorHeur()){
					actual=nuevoEstado;
					if (nuevoEstado.getValorHeur()<mejorHastaAhora.getValorHeur())
						mejorHastaAhora=nuevoEstado;
					sucesores.clear();
					sucesores=nuevoEstado.expandir();
					expandidos++;
					generados+=sucesores.size();
				}
				else{
					double P=Math.pow((double)Math.E,(double)-e/T);
					double N=rand.nextDouble();
					if (N<P){
						actual=nuevoEstado;
						sucesores.clear();
						sucesores=nuevoEstado.expandir();
						expandidos++;
						generados+=sucesores.size();
					}
					else
						sucesores.remove(r);
				}
			}
			T-=0.001;
		}
		return mejorHastaAhora;
	}
	
	public static void main(String[] args){
		Juego inicial=new Puzzle8();
		EnfriamientoSimulado busqueda=new EnfriamientoSimulado();
		System.out.println("Enfriamiento simulado:\n");
		System.out.println("Inicial: "+inicial.toString());
		Juego solucion=(Puzzle8)busqueda.resuelve(inicial);
		if (solucion.isGoal()){
			System.out.println(solucion.getCamino());
			System.out.println("Solución: "+solucion.toString());
		}
		else
			System.out.println("No se encontró solución");
	}
	
}