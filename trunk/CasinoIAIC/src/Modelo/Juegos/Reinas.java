package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego de las 8 reinas. Se trata de colocar 8 reinas en un tablero de 8x8, sin que una reina amenace a cualquiera de las demás.
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class Reinas extends Juego{
	/**
	 * El estado se representa mediante un array de 8 posiciones. Cada posición corresponde a una fila del tablero, y contiene
	 * el número de columna donde hay una reina.
	 */
	private int[] reinas;
	
	/**
	 * Constructora de estado inicial
	 */
	public Reinas(){
		reinas=new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de copia, para los sucesores.
	 * @param juego estado padre
	 * @param cos coste acumulado
	 * @param cam camino hasta este estado
	 */
	private Reinas(Reinas juego,int cos,String cam){
		reinas=new int[8];
		for (int i=0;i<reinas.length;i++)
			reinas[i]=juego.reinas[i];
		profundidad=juego.profundidad+1;
		coste=juego.coste+cos;
		camino=juego.camino+cam;
	}
	
	/**
	 * Método que devuelve una lista de posibles colocaciones en la fila p
	 * @param p número de fila donde se quiere colocar una reina
	 * @return lista de posibles columnas donde se puede colocar la reina
	 */
	private Vector<Integer> lista(int p){
		Vector<Integer> v=new Vector<Integer>(0,1);
		for (int i=0;i<reinas.length;i++){
			reinas[p]=i;
			if (test(p))
				v.add(i);
		}
		reinas[p]=-1;
		return v;
	}
	
	/**
	 * Método que comprueba si la reina colocada en la fila p amenaza a alguna otra
	 * @param p fila donde se encuentra la reina
	 * @return true si no amenaza, false si amenaza
	 */
	private boolean test(int p){
		int c=reinas[p];
		// comprobar la columna
		for (int i=0;i<reinas.length;i++)
			if (reinas[i]==c && i!=p)
				return false;
		// comprobar diagonal \
		int a;
		int b;
		if (p<c){
			a=0;
			b=c-p;
		}
		else{
			b=0;
			a=p-c;
		}
		for (int i=a;i<reinas.length;i++){
			if (reinas[i]==b && i!=p)
				return false;
			b++;
		}
		// comprobar diagonal /
		if (p<(7-c)){
			b=0;
			a=p+c;
		}
		else{
			a=7;
			b=c-(7-p);
		}
		for (int i=a;i>-1;i--){
			if (reinas[i]==b && i!=p)
				return false;
			b++;
		}
		return true;
	}
	
	/**
	 * Operador que cambia el valor de una fila
	 * @param p fila donde va a poner la reina
	 * @param v columna donde se encuentra la reina
	 */
	private void ponValor(int p,int v){
		reinas[p]=v;
		valorHeur=this.Heuristica();
	}

	/**
	 * Función heurística, calcula el número de reinas que no han sido colocadas todavía
	 * @return número de reinas sin colocar
	 */
	private double Heuristica(){
		double d=0;
		for (int i=0;i<reinas.length;i++)
			if (reinas[i]==-1)
				d+=1;
		return d;
	}
	
	public Vector<Juego> expandir() {
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Reinas estado;
		int p=0;
		while (reinas[p]!=-1 && p<reinas.length)
			p++;
		Vector<Integer> lista=this.lista(p);
		for (int i=0;i<lista.size();i++){
			estado=new Reinas(this,1,"Colocar reina en fila "+p+" y columna "+lista.elementAt(i)+"\n");
			estado.ponValor(p,lista.elementAt(i));
			sucesores.add(estado);
		}
		return sucesores;
	}

	public boolean isGoal() {
		for (int i=0;i<reinas.length;i++)
			if (reinas[i]==-1)
				return false;
		return true;
	}

	public boolean equals(Object o) {
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Reinas estado=(Reinas)o;
		for (int i=0;i<reinas.length;i++)
			if (reinas[i]!=estado.reinas[i])
				return false;
		return true;
	}
	
	public String toString() {
		String s="8 Reinas\n[ ";
		for (int i=0;i<reinas.length;i++)
			s+=reinas[i]+" ";
		s+="]\n";
		return s;
	}
	
}