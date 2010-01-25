package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego de los misioneros y los caníbales. Tenemos 2 orillas, un barco, y un grupo de caníbales y misioneros que quieren cruzar de orilla. En el barco solo
 * caben 2 personas, y si en una orilla hay menos misioneros que caníbales, estos corren peligro.
 * @author Pablo Acevedo
 *
 */

public class Misioneros extends Juego{
	/**
	 * NMI= número de misioneros en la orilla izquierda
	 */
	private int NMI;
	/**
	 * NCI= número de caníbales en la orilla izquierda
	 */
	private int NCI;
	/**
	 * BAR= orilla donde se encuentra el barco. 0=derecha, 1=izquierda
	 */
	private int BAR;
	
	/**
	 * Constructora del estado inicial, pone todo a 0 y el estado al inicio del juego
	 */
	public Misioneros(){
		NMI=3;
		NCI=3;
		BAR=1;
		valorHeur=-1; // no tiene heuristica
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor. Sólo se usa cuando se va a crear un sucesor, calcula los valores del nuevo estado. El numero de misioneros,
	 * caníbales y barco se calculan en los operadores.
	 * @param m estado padre
	 * @param cos coste acumulado
	 * @param cam camino acumulado
	 */
	private Misioneros(Misioneros m,int cos,String cam){
		NMI=m.NMI;
		NCI=m.NCI;
		BAR=m.BAR;
		coste=m.coste+cos;
		profundidad=m.profundidad+1;
		camino=m.camino+cam;
	}
	
	/**
	 * Cálculo de los estados de peligro, que se dan si hay menos misioneros que caníbales en alguna orilla
	 * @param m número de misioneros a la izquierda
	 * @param c número de caníbales a la izquierda
	 * @return true si hay peligro, false si no lo hay
	 */
	private boolean peligro(int m,int c){
		return (m<c && m>0)||(m>c && m<3);
	}
	
	/**
	 * Exploración del estado, según el operador que se quiera aplicar, se devuelve si sería posible o no hacer esa operación.
	 * 
	 * @param s operación que se quiere aplicar
	 * @return true si la operación es posible, false si no lo es
	 */
	private boolean puedo(String s){
		// Mover 2 misioneros
		if (s.equals("MM")){
			if (BAR==1)
				return NMI>1 && !peligro(NMI-2,NCI);
			else
				return NMI<2 && !peligro(NMI+2,NCI);
		}
		// Mover 2 caníbales
		if (s.equals("CC")){
			if (BAR==1)
				return NCI>1 && !peligro(NMI,NCI-2);
			else
				return NCI<2 && !peligro(NMI,NCI+2);
		}
		// Mover 1 misionero y 1 caníbal
		if (s.equals("MC")){
			if (BAR==1)
				return NMI>0 && NCI>0 && !peligro(NMI-1,NCI-1);
			else
				return NMI<3 && NCI<3 && !peligro(NMI+1,NCI+1);
		}
		// Mover 1 caníbal
		if (s.equals("C")){
			if (BAR==1)
				return NCI>0 && !peligro(NMI,NCI-1);
			else
				return NCI<3 && !peligro(NMI,NCI+1);
		}
		// Mover 1 misionero
		if (s.equals("M")){
			if (BAR==1)
				return NMI>0 && !peligro(NMI-1,NCI);
			else
				return NMI<3 && !peligro(NMI+1,NCI);
		}
		return false;
	}

	/**
	 * Operador que mueve de orilla a 2 misioneros
	 */
	private void mueveMM(){
		if (BAR==1){
			NMI-=2;
			BAR=0;
		}
		else{
			NMI+=2;
			BAR=1;
		}
	}
	
	/**
	 * Operador que mueve de orilla a 1 misionero
	 */
	private void mueveM(){
		if (BAR==1){
			NMI--;
			BAR=0;
		}
		else{
			NMI++;
			BAR=1;
		}
	}
	
	/**
	 * Operador que mueve de orilla a 1 misionero y 1 caníbal
	 */
	private void mueveMC(){
		if (BAR==1){
			NMI--;
			NCI--;
			BAR=0;
		}
		else{
			NMI++;
			NCI++;
			BAR=1;
		}
	}
	
	/**
	 * Operador que mueve de orilla a 2 caníbales
	 */
	private void mueveCC(){
		if (BAR==1){
			NCI-=2;
			BAR=0;
		}
		else{
			NCI+=2;
			BAR=1;
		}
	}
	
	/**
	 * Operador que mueve de orilla a 1 caníbal
	 */
	private void mueveC(){
		if (BAR==1){
			NCI--;
			BAR=0;
		}
		else{
			NCI++;
			BAR=1;
		}
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Misioneros estado;
		if (this.puedo("M")){
			estado=new Misioneros(this,1,"Mover 1 misionero\n");
			estado.mueveM();
			sucesores.add(estado);
		}
		if (this.puedo("MM")){
			estado=new Misioneros(this,1,"Mover 2 misioneros\n");
			estado.mueveMM();
			sucesores.add(estado);
		}
		if (this.puedo("MC")){
			estado=new Misioneros(this,1,"Mover 1 misionero y 1 caníbal\n");
			estado.mueveMC();
			sucesores.add(estado);
		}
		if (this.puedo("CC")){
			estado=new Misioneros(this,1,"Mover 2 caníbales\n");
			estado.mueveCC();
			sucesores.add(estado);
		}
		if (this.puedo("C")){
			estado=new Misioneros(this,1,"Mover 1 caníbal\n");
			estado.mueveC();
			sucesores.add(estado);
		}
		return sucesores;
	}
	
	public boolean isGoal(){
		return (NMI==0 && NCI==0 && BAR==0);
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Misioneros estado=(Misioneros)o;
		return (NMI==estado.NMI && NCI==estado.NCI && BAR==estado.BAR);
	}
	
	public String toString(){
		return "Misioneros y caníbales\nMisioneros izquierda: "+NMI+" Canibales izquierda: "+NCI+"\n";
	}
}