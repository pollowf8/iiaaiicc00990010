package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego del sudoku. En cada posición sólo puede haber un único número del 1 al 9, que no se repetirá ni en la fila, ni en la columna, ni en el cuadrado 3x3 que se 
 * encuentre.
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class Sudoku extends Juego{
	/**
	 * El estado se representa con un array de 81 posiciones, si hay un 0 corresponde a un hueco
	 */
	private int[] tablero;
	
	/**
	 * Constructora de estado inicial
	 */
	public Sudoku(){
		tablero=new int[]  {0,0,2,0,3,1,0,0,8,
							0,4,0,0,0,0,0,1,3,
							8,1,0,7,0,0,0,0,0,
							0,3,0,0,5,0,0,6,2,
							4,0,7,3,8,6,9,0,1,
							6,5,0,0,2,0,0,8,0,
							0,0,0,0,0,5,0,7,9,
							2,7,0,0,0,0,0,3,0,
							9,0,0,4,7,0,6,0,0};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor
	 * @param juego estado padre
	 * @param cos coste acumulado
	 * @param cam camino hasta el estado
	 */
	private Sudoku(Sudoku juego,int cos,String cam){
		tablero=new int[81];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=juego.tablero[i];
		profundidad=juego.profundidad+1;
		coste=juego.coste+cos;
		camino=juego.camino+cam;
	}
	
	/**
	 * Método que devuelve una lista con los posibles valores para el hueco p
	 * @param p posición para el que se calculan los posibles valores
	 * @return lista de candidatos en el hueco p
	 */
	private Vector<Integer> lista(int p){
		Vector<Integer> v=new Vector<Integer>(0,1);
		for (int i=0;i<9;i++){
			tablero[p]=i+1;
			if (test(p))
				v.add(i+1);
		}
		tablero[p]=0;
		return v;
	}
	
	/**
	 * Método que comprueba si el valor que ocupa la posición p está repetido en su fila, columna o cuadrado 3x3
	 * @param p posición para la que se hace la comprobación
	 * @return true si no hay ninguna coincidencia, false si hay alguna.
	 */
	private boolean test(int p){
		int v=tablero[p];
		int fila=p/9;
		int columna=p-(fila*9);
		// comprobacion de linea horizontal
		for (int i=fila*9;i<fila*9+9;i++)
			if (i!=p && tablero[i]==v)
				return false;
		// comprobacion de linea vertical
		for (int i=columna;i<tablero.length;i+=9)
			if (i!=p && tablero[i]==v)
				return false;
		// comprobacion de cuadrado 3x3
		int filaI=(fila/3)*27;
		int filaF=((fila/3)+1)*27;
		int columnaI=(columna/3)*3;
		int columnaF=((columna/3)+1)*3;
		for (int i=filaI;i<filaF;i+=9)
			for (int j=columnaI;j<columnaF;j++)
				if ((i+j)!=p && tablero[i+j]==v)
					return false;
		return true;
	}
	
	/**
	 * Operador que cambia el valor de la posicion p por i
	 * @param p posición que va a cambiar su valor
	 * @param i valor nuevo
	 */
	private void ponValor(int p,int i){
		tablero[p]=i;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Función heurística, devuelve el número de huecos sin rellenar.
	 * @return número de huecos vacíos
	 */
	private double Heuristica(){
		double d=0;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]==0)
				d+=1;
		return d;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Sudoku estado;
		int p=0;
		while (tablero[p]!=0 && p<tablero.length)
			p++;
		Vector<Integer> lista=this.lista(p);
		if (!lista.isEmpty()){
			for (int i=0;i<lista.size();i++){
				estado=new Sudoku(this,1,"Colocar "+lista.elementAt(i)+" en "+p+"\n");
				estado.ponValor(p,lista.elementAt(i));
				sucesores.add(estado);
			}
		}
		return sucesores;
	}
	
	public boolean isGoal(){
		for (int i=0;i<tablero.length;i++)
			if (!test(i) || tablero[i]==0)
				return false;
		return true;
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Sudoku estado=(Sudoku)o;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]!=estado.tablero[i])
				return false;
		return true;
	}
	
	public String toString(){
		String s="Sudoku\n";
		for (int i=0;i<tablero.length;i+=9){
			for (int j=0;j<3;j++){
				s+=tablero[i+3*j]+" "+tablero[i+3*j+1]+" "+tablero[i+3*j+2];
				if (j<2)
					s+="|";
			}
			s+="\n";
			if (i==18 || i==45)
				s+="-----------------\n";
		}
		return s;
	}
}