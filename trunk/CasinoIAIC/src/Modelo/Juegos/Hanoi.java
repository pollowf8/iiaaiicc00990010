package Modelo.Juegos;

import java.util.Stack;
import java.util.Vector;

/**
 * Juego de las torres de hanoi. Se emplean 3 varillas y 4 discos. Para la representación en string se toma la cima de la pila como el elemento más a la derecha
 * @author Pablo Acevedo
 *
 */
public class Hanoi extends Juego{
	/**
	 * Varilla izquierda
	 */
	private Stack<Integer> varillaA;
	/**
	 * Varilla central
	 */
	private Stack<Integer> varillaB;
	/**
	 * Varilla derecha
	 */
	private Stack<Integer> varillaC;
	
	/**
	 * Constructora del estado inicial. Apila los 4 discos en orden en la varilla A
	 */
	public Hanoi(){
		varillaA=new Stack<Integer>();
		varillaB=new Stack<Integer>();
		varillaC=new Stack<Integer>();
		for (int i=4;i>0;i--)
			varillaA.push(i);
		valorHeur=-1;
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor. Copia las pilas en orden y añade coste, profundidad y camino
	 * @param h estado padre
	 * @param cos coste acumulado
	 * @param cam camino acumulado
	 */
	private Hanoi(Hanoi h,int cos,String cam){
		varillaA=new Stack<Integer>();
		for (int i=0;i<h.varillaA.size();i++)
			varillaA.push(h.varillaA.elementAt(i));
		varillaB=new Stack<Integer>();
		for (int i=0;i<h.varillaB.size();i++)
			varillaB.push(h.varillaB.elementAt(i));
		varillaC=new Stack<Integer>();
		for (int i=0;i<h.varillaC.size();i++)
			varillaC.push(h.varillaC.elementAt(i));
		coste=h.coste+cos;
		profundidad=h.profundidad+1;
		camino=h.camino+cam;
	}

	/**
	 * Comprobación de estado peligroso. Si el elemento que se quiere mover es mayor que la cima de la pila, el movimiento está prohibido
	 * @param A pila donde se quiere introducir el elemento
	 * @param B elemento que se quiere introducir
	 * @return true si hay peligro, false en caso contrario
	 */
	private boolean peligro(Stack<Integer> A,int B){
		if (A.size()>0)
			return (A.peek()<B);
		return false;
	}
	
	/**
	 * Exploración del estado, según la operación que se quiera aplicar, se devuelve si sería posible o no hacerla.
	 * @param s operación que se quiere comprobar
	 * @return true si se puede aplicar la operacion, false si no se puede
	 */
	private boolean puedo(String s){
		// Mover disco de varillaA a varillaB
		if (s.equals("AB")){
			if (varillaA.size()>0)
				return !peligro(varillaB,varillaA.peek());
		}
		// Mover disco de varillaA a varillaC
		if (s.equals("AC")){
			if (varillaA.size()>0)
				return !peligro(varillaC,varillaA.peek());
		}
		// Mover disco de varillaB a varillaA
		if (s.equals("BA")){
			if (varillaB.size()>0)
				return !peligro(varillaA,varillaB.peek());
		}
		// Mover disco de varillaB a varillaC
		if (s.equals("BC")){
			if (varillaB.size()>0)
				return !peligro(varillaC,varillaB.peek());
		}
		// Mover disco de varillaC a varillaA
		if (s.equals("CA")){
			if (varillaC.size()>0)
				return !peligro(varillaA,varillaC.peek());
		}
		// Mover disco de varillaC a varillaB
		if (s.equals("CB")){
			if (varillaC.size()>0)
				return !peligro(varillaB,varillaC.peek());
		}
		return false;
	}

	/**
	 * Mover un disco de A a B
	 */
	private void AB(){
		int cima=varillaA.pop();
		varillaB.push(cima);
	}
	
	/**
	 * Mover un disco de A a C
	 */
	private void AC(){
		int cima=varillaA.pop();
		varillaC.push(cima);
	}
	
	/**
	 * Mover un disco de B a A
	 */
	private void BA(){
		int cima=varillaB.pop();
		varillaA.push(cima);
	}
	
	/**
	 * Mover un disco de B a C
	 */
	private void BC(){
		int cima=varillaB.pop();
		varillaC.push(cima);
	}
	
	/**
	 * Mover un disco de C a A
	 */
	private void CA(){
		int cima=varillaC.pop();
		varillaA.push(cima);
	}
	
	/**
	 * Mover un disco de C a B
	 */
	private void CB(){
		int cima=varillaC.pop();
		varillaB.push(cima);
	}

	public Vector expandir(){
		Vector<Hanoi> sucesores=new Vector<Hanoi>(0,1);
		Hanoi estado;
		// Mover cima de A a B
		if (this.puedo("AB")){
			estado=new Hanoi(this,1,"A->B\n");
			estado.AB();
			sucesores.add(estado);
		}
		// Mover cima de A a C
		if (this.puedo("AC")){
			estado=new Hanoi(this,1,"A->C\n");
			estado.AC();
			sucesores.add(estado);
		}
		// Mover cima de B a A
		if (this.puedo("BA")){
			estado=new Hanoi(this,1,"B->A\n");
			estado.BA();
			sucesores.add(estado);
		}
		// Mover cima de B a C
		if (this.puedo("BC")){
			estado=new Hanoi(this,1,"B->C\n");
			estado.BC();
			sucesores.add(estado);
		}
		// Mover cima de C a A
		if (this.puedo("CA")){
			estado=new Hanoi(this,1,"C->A\n");
			estado.CA();
			sucesores.add(estado);
		}
		// Mover cima de C a B
		if (this.puedo("CB")){
			estado=new Hanoi(this,1,"C->B\n");
			estado.CB();
			sucesores.add(estado);
		}
		return sucesores;
	}

	public boolean isGoal(){
		return (varillaA.isEmpty() && varillaB.isEmpty() && !varillaC.isEmpty());
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Hanoi estado=(Hanoi)o;
		return (this.varillaA.equals(estado.varillaA) && this.varillaB.equals(estado.varillaB) && this.varillaC.equals(estado.varillaC));
	}
	
	public String toString(){
		String aux="Varilla A=[";
		for(int i=0;i<varillaA.size();i++)
			aux+=varillaA.elementAt(i);
		aux+="] ; Varilla B=[";
		for(int i=0;i<varillaB.size();i++)
			aux+=varillaB.elementAt(i);
		aux+="] ; Varilla C=[";
		for(int i=0;i<varillaC.size();i++)
			aux+=varillaC.elementAt(i);
		aux+="]\n";
		return aux;
	}
	
}