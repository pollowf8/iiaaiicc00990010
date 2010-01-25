package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego del lobo, la cabra y la col. Un granjero ha comprado un lobo, una cabra y una col. Para volver a casa tiene que cruzar un río, para lo cual dispone de una
 * barca. La barca sólo tiene capacidad para él mismo y una de sus compras. Si el lobo se queda sin vigilancia, se come a la cabra. Si la cabra queda sin vigilancia,
 * se come la col.
 * @author Pablo Acevedo
 *
 */
public class LoboCabraCol extends Juego{
	/**
	 * granjero=0 si está en la orilla derecha, 1 si está en la izquierda
	 */
	private int granjero;
	/**
	 * lobo=0 si está en la orilla derecha, 1 si está en la izquierda
	 */
	private int lobo;
	/**
	 * cabra=0 si está en la orilla derecha, 1 si está en la izquierda
	 */
	private int cabra;
	/**
	 * col=0 si está en la orilla derecha, 1 si está en la izquierda
	 */
	private int col;
	
	/**
	 * Constructora de estado inicial. Inicialmente el granjero, el lobo, la cabra y la col están en la orilla izquierda
	 */
	public LoboCabraCol(){
		granjero=1;
		lobo=1;
		cabra=1;
		col=1;
		valorHeur=-1;
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor. Copia al estado padre
	 * @param l estado padre
	 * @param cos coste acumulado
	 * @param cam camino acumulado
	 */
	private LoboCabraCol(LoboCabraCol l,int cos,String cam){
		granjero=l.granjero;
		lobo=l.lobo;
		cabra=l.cabra;
		col=l.col;
		coste=l.coste+cos;
		profundidad=l.profundidad+1;
		camino=l.camino+cam;
	}
	
	/**
	 * Comprobación de peligro. Existe peligro si el lobo y la cabra quedan sin vigilancia del granjero, también si cabra y col quedan sin vigilancia
	 * del granjero.
	 * @param g posición del granjero
	 * @param l posición del lobo
	 * @param ca posición de la cabra
	 * @param co posición de la col
	 * @return true si hay peligro, false si no lo hay
	 */
	private boolean peligro(int g,int l,int ca,int co){
		return (l==ca && g!=l)||(ca==co && g!=ca);
	}
	
	/**
	 * Exploración del estado, según el operador que se quiera aplicar, se devuelve si sería posible o no hacer esa operación.
	 * 
	 * @param s operador que se quiere aplicar
	 * @return true si se puede, false si no
	 */
	private boolean puedo(String s){
		// Mover solo granjero
		if (s.equals("G")){
			if (granjero==1)
				return !peligro(0,lobo,cabra,col);
			else
				return !peligro(1,lobo,cabra,col);
		}
		// Mover granjero y lobo
		if (s.equals("GL")){
			if (granjero==1 && granjero==lobo)
				return !peligro(0,0,cabra,col);
			if (granjero==0 && granjero==lobo)
				return !peligro(1,1,cabra,col);
		}
		// Mover granjero y cabra
		if (s.equals("GCa")){
			if (granjero==1 && granjero==cabra)
				return !peligro(0,lobo,0,col);
			if (granjero==0 && granjero==cabra)
				return !peligro(1,lobo,1,col);
		}
		// Mover granjero y col
		if (s.equals("GCo")){
			if (granjero==1 && granjero==col)
				return !peligro(0,lobo,cabra,0);
			if (granjero==0 && granjero==col)
				return !peligro(1,lobo,cabra,1);
		}
		return false;
	}
	
	/**
	 * Operador que mueve al granjero de orilla
	 */
	private void G(){
		if (granjero==1)
			granjero=0;
		else
			granjero=1;
	}
	
	/**
	 * Operador que mueve al granjero y al lobo de orilla 
	 */
	private void GL(){
		if (granjero==1){
			granjero=0;
			lobo=0;
		}
		else{
			granjero=1;
			lobo=1;
		}
	}
	
	/**
	 * Operador que mueve al granjero y la cabra de orilla
	 */
	private void GCa(){
		if (granjero==1){
			granjero=0;
			cabra=0;
		}
		else{
			granjero=1;
			cabra=1;
		}
	}
	
	/**
	 * Operador que mueve al granjero y la col de orilla
	 */
	private void GCo(){
		if (granjero==1){
			granjero=0;
			col=0;
		}
		else{
			granjero=1;
			col=1;
		}
	}

	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		LoboCabraCol estado;
		if (this.puedo("G")){
			estado=new LoboCabraCol(this,1,"Mueve granjero\n");
			estado.G();
			sucesores.add(estado);
		}
		if (this.puedo("GL")){
			estado=new LoboCabraCol(this,1,"Mueve granjero y lobo\n");
			estado.GL();
			sucesores.add(estado);
		}
		if (this.puedo("GCa")){
			estado=new LoboCabraCol(this,1,"Mueve granjero y cabra\n");
			estado.GCa();
			sucesores.add(estado);
		}
		if (this.puedo("GCo")){
			estado=new LoboCabraCol(this,1,"Mueve granjero y col\n");
			estado.GCo();
			sucesores.add(estado);
		}
		return sucesores;
	}

	public boolean isGoal(){
		return (granjero==0 && cabra==0 && col==0 && lobo==0);
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		LoboCabraCol estado=(LoboCabraCol)o;
		return (granjero==estado.granjero && lobo==estado.lobo && cabra==estado.cabra && col==estado.col);
	}
	
	public String toString(){
		return "Lobo, cabra, col\nGranjero="+granjero+" ; Lobo="+lobo+" ; Cabra="+cabra+" ; Col="+col+"\n";
	}
}