package Modelo.Juegos;
/* FALTA LA HEURISTICA */
import java.util.Vector;

/**
 * Juego del puzzle de 8. Se dispone de un tablero de 3*3 huecos, y tenemos 8 fichas, de forma que hay un hueco libre sobre el que se puede deslizar una
 * ficha cada vez. 
 * @author Pablo Acevedo
 *
 */
public class Puzzle8 extends Juego{
	/**
	 * tablero de juego, en cada posición contiene el valor asociado a la ficha que la ocupa (1, 2, 3,...). El hueco vacío se codifica con un 0
	 */
	private int[] tablero;
	/**
	 * estado objetivo
	 */
	private int[] goal;
	
	/**
	 * Constructora de estado inicial. Crea el tablero inicial y pone a 0 el resto de valores.
	 */
	public Puzzle8(){
		tablero=new int[]{2,8,3,1,6,4,7,0,5};
		goal=new int[]{1,2,3,8,0,4,7,6,5};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de un sucesor, introduce cambios para el arbol, el tablero/heuristica lo modificaran los operadores
	 * 
	 * @param puzzle estado padre
	 * @param cos coste acumulado
	 * @param cam camino acumulado
	 */
	private Puzzle8(Puzzle8 puzzle,int cos,String cam){
		tablero=new int[9];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=puzzle.tablero[i];
		goal=new int[9];
		for (int i=0;i<goal.length;i++)
			goal[i]=puzzle.goal[i];
		coste=puzzle.coste+cos;
		profundidad=puzzle.profundidad+1;
		camino=puzzle.camino+cam;
	}

	/**
	 * Devuelve el valor de lo que hay en la posicion pos
	 * @param pos posicion del tablero
	 * @return valor del tablero
	 */
	private int getValor(int pos){
		return tablero[pos];
	}
	
	/**
	 * Devuelve la posicion en la que se encuentra un valor determinado
	 * @param valor valor que se va a buscar en el tablero
	 * @return posición en la que se encuentra el parámetro valor
	 */
	private int getPos(int valor){
		int pos=0;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]==valor)
				pos=i;
		return pos;
	}
	
	/**
	 * Exploración del estado, según el operador que se quiera aplicar, se devuelve si es posible o no realizar la operación
	 * @param s operador que se quiere aplicar
	 * @return true si se puede aplicar, false si no
	 */
	private boolean puedo(String s){
		int pos=this.getPos(0);
		// Mover el hueco arriba
		if (s.equals("Up")){
			if (pos>2)
				return true;
		}
		// Mover el hueco abajo
		if (s.equals("Down")){
			if (pos<6)
				return true;
		}
		// Mover el hueco a la derecha
		if (s.equals("Right")){
			if (pos<2 || (pos>2 && pos<5) || (pos>5 && pos<8))
				return true;
		}
		// Mover el hueco a la izquierda
		if (s.equals("Left")){
			if ((pos>0 && pos<3) || (pos>3 && pos<6) || pos>6)
				return true;
		}
		return false;	
	}

	/**
	 * Operador que intercambia el hueco con la ficha que tenga encima
	 */
	private void mueveUp(){
		int pos=this.getPos(0);
		tablero[pos]=tablero[pos-3];
		tablero[pos-3]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Operador que intercambia el hueco con la ficha que tenga debajo
	 */
	private void mueveDown(){
		int pos=this.getPos(0);
		tablero[pos]=tablero[pos+3];
		tablero[pos+3]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Operador que intercambia el hueco con la ficha que tenga a la derecha
	 */
	private void mueveRight(){
		int pos=this.getPos(0);
		tablero[pos]=tablero[pos+1];
		tablero[pos+1]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Operador que intercambia el hueco con la ficha que tenga a la izquierda
	 */
	private void mueveLeft(){
		int pos=this.getPos(0);
		tablero[pos]=tablero[pos-1];
		tablero[pos-1]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Método auxiliar de la heurística, devuelve la distancia en horizontal entre dos posiciones
	 * @param p1 posicion 1
	 * @param p2 posicion 2
	 * @return distancia horizontal entre p1 y p2, en valor absoluto
	 */
	private int getX(int p1,int p2){
		int x1=p1%3;
		int x2=p2%3;
		return Math.abs(x1-x2);
	}
	
	/**
	 * Método auxiliar de la heurística, devuelve la distancia en vertical entre dos posiciones
	 * @param p1 posicion 1
	 * @param p2 posicion 2
	 * @return distancia vertical entre p1 y p2, en valor absoluto
	 */
	private int getY(int p1,int p2){
		int y1=p1%3;
		int y2=p2%3;
		return Math.abs(y1-y2);
	}

	/**
	 * Función heurística. Sigue el método Manhattan, devuelve la suma de las distancias de las posiciones actuales a las del objetivo. Además se ha mejorado
	 * con la suma de otra heurística, el número de fichas descolocadas.
	 * @param goal estado objetivo
	 * @return suma de distancias. Cuanto más pequeño sea el número, más cerca se está del objetivo.
	 */
	public double Heuristica(){
		int dist=0;
		for (int i=0;i<tablero.length;i++) {
			int valor=tablero[i];
			int pos=0;
			
			for (int j=0;j<goal.length;j++)
				if (goal[j]==valor)
					pos=j;
			// distancia en horizontal
			int dh=getX(i,pos);
			// distancia en vertical
			int dv=getY(i,pos);
			dist+=dv+dh;
		}
		return dist;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores = new Vector<Juego>();
		if (this.puedo("Left")){
			Puzzle8 siguiente=new Puzzle8(this,1,"Izquierda\n");
			siguiente.mueveLeft();
			sucesores.add(siguiente);
		}
		if (this.puedo("Up")){
			Puzzle8 siguiente=new Puzzle8(this,1,"Arriba\n");
			siguiente.mueveUp();
			sucesores.add(siguiente);
		}
		if (this.puedo("Right")){
			Puzzle8 siguiente=new Puzzle8(this,1,"Derecha\n");
			siguiente.mueveRight();
			sucesores.add(siguiente);
		}
		if(this.puedo("Down")){
			Puzzle8 siguiente=new Puzzle8(this,1,"Abajo\n");
			siguiente.mueveDown();
			sucesores.add(siguiente);
		}
		return sucesores;
	}

	public boolean isGoal(){
		return (tablero[0]==1 && tablero[1]==2 && tablero[2]==3 &&
				tablero[3]==8 && tablero[4]==0 && tablero[5]==4 &&
				tablero[6]==7 && tablero[7]==6 && tablero[8]==5);
	}
	
	public String toString(){
		return "Puzzle 8\n["+tablero[0]+" "+tablero[1]+" "+tablero[2]+"]["
				+tablero[3]+" "+tablero[4]+" "+tablero[5]+"]["
				+tablero[6]+" "+tablero[7]+" "+tablero[8]+"]\n";	
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Puzzle8 estado=(Puzzle8)o;
		for (int i=0;i<tablero.length;i++)
			if (this.getValor(i)!=estado.getValor(i))
				return false;
		return true;
	}
}