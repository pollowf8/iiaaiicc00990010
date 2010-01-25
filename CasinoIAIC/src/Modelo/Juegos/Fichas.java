package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego de las fichas. Se dispone de un tablero de 7 posiciones, 3 fichas de tipo 1, y 3 fichas de tipo 2. Las fichas están agrupadas por tipo en cada extremo del
 * tablero, quedando un hueco en el centro. El juego consiste en llegar a tener todas las fichas de tipo 2 a la izquierda de todas las fichas de tipo 1, la
 * posición del hueco no importa. Para ello, una ficha se puede mover al hueco, si es adyacente, o bien puede saltar por encima de otra, o saltar por encima de 2
 * fichas, siempre que caiga en el hueco.
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class Fichas extends Juego{
	/**
	 * El estado se representa con un array de 7 posiciones, 1 si la posición la ocupa una ficha tipo 1, 2 si la ocupa una ficha tipo 2, 0 si hueco.
	 */
	private int[] tablero;
	
	/**
	 * Constructora de estado inicial
	 */
	public Fichas(){
		tablero=new int[]{1,1,1,0,2,2,2};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}

	/**
	 * Constructora de sucesor
	 * @param ficha estado padre
	 * @param cos coste acumulado
	 * @param cam camino hasta este estado
	 */
	private Fichas(Fichas ficha,int cos,String cam){
		tablero=new int[7];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=ficha.tablero[i];
		coste=ficha.coste+cos;
		profundidad=ficha.profundidad+1;
		camino=ficha.camino+cam;
	}
	
	/**
	 * Método que comprueba si se puede aplicar la operación dada por 's'. Para simplificar la comprobación, así como el operador, todas las posibles operaciones
	 * son mover el hueco 1, 2 o 3 posiciones a izquierda/derecha.
	 * @param s operacion que se quiere comprobar
	 * @return true si aplicable, false en caso contrario.
	 */
	private boolean puedo(String s){
		int p=0;
		while (tablero[p]!=0 && p<tablero.length)
			p++;
		// mover el hueco a la derecha.
		if (s.equals("Md")){
			if (p>5)
				return false;
		}
		// mover el hueco a la izquierda
		if (s.equals("Mi")){
			if (p<1)
				return false;
		}
		// saltar 1 ficha a la derecha.
		if (s.equals("S1d")){
			if (p>4)
				return false;
		}
		// saltar 1 ficha a la izquierda.
		if (s.equals("S1i")){
			if (p<2)
				return false;
		}
		// saltar 2 fichas a la derecha.
		if (s.equals("S2d")){
			if (p>3)
				return false;
		}
		// saltar 2 fichas a la izquierda.
		if (s.equals("S2i")){
			if (p<3)
				return false;
		}
		return true;
	}
	
	/**
	 * Operador que hace saltar al hueco a la posición dada por i
	 * @param i posición que se intercambia con el hueco.
	 */
	private void Salta(int i){
		// buscar el hueco
		int p=0;
		while (tablero[p]!=0 && p<tablero.length)
			p++;
		tablero[p]=tablero[i];
		tablero[i]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Función heurística. Calcula el número de fichas de tipo 2 que se encuentran a la derecha de las de tipo 1
	 * @return número de fichas tipo 2 a la derecha de alguna ficha tipo 1
	 */
	private double Heuristica(){
		double d=0;
		// buscar el primer 1
		int p=0;
		while (tablero[p]!=1 && p<tablero.length)
			p++;
		for (int i=0;i<tablero.length;i++){
			if (tablero[i]==2 && i>p)
				d+=1;
		}
		return d;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Fichas estado;
		// buscar el hueco
		int i=0;
		while (tablero[i]!=0 && i<tablero.length)
			i++;
		if (this.puedo("Md")){
				estado=new Fichas(this,1,"Mover "+(i+1)+" a la izquierda\n");
				estado.Salta(i+1);
				sucesores.add(estado);
		}
		if (this.puedo("Mi")){
				estado=new Fichas(this,1,"Mover "+(i-1)+" a la derecha\n");
				estado.Salta(i-1);
				sucesores.add(estado);
		}
		if (this.puedo("S1d")){
				estado=new Fichas(this,1,"Salta "+(i+2)+" por encima de "+(i+1)+"\n");
				estado.Salta(i+2);
				sucesores.add(estado);
		}
		if (this.puedo("S1i")){
				estado=new Fichas(this,1,"Salta "+(i-2)+" por encima de "+(i-1)+"\n");
				estado.Salta(i-2);
				sucesores.add(estado);
		}
		if (this.puedo("S2d")){
				estado=new Fichas(this,2,"Salta "+(i+3)+" por encima de "+(i+2)+", "+(i+1)+"\n");
				estado.Salta(i+3);
				sucesores.add(estado);
		}
		if (this.puedo("S2i")){
				estado=new Fichas(this,2,"Salta "+(i-3)+" por encima de "+(i-2)+", "+(i-1)+"\n");
				estado.Salta(i-3);
				sucesores.add(estado);
		}
		return sucesores;
	}

	public boolean isGoal(){
		return valorHeur==0;
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Fichas estado=(Fichas)o;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]!=estado.tablero[i])
				return false;
		return true;
	}
	
	public String toString(){
		String s="Fichas\n[ ";
		for (int i=0;i<tablero.length;i++)
			s+=tablero[i]+" ";
		s+="]\n";
		return s;
	}
	
}