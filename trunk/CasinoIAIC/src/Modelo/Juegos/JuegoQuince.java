package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego del quince. Se dispone de un tablero con 16 cartas, numeradas del 1 al 10. Con cada carta, se pueden hacer 3 operaciones: quitar 2 cartas que sean iguales,
 * sumarla con otra más si la suma vale 15, y sumar 3 cartas hasta que la suma sea 15. El objetivo del juego es minimizar el número de cartas que quedan al final
 * en el tablero.
 * @author Pablo Acevedo, Alfredo Díez, Jorge Guirado
 *
 */
public class JuegoQuince extends Juego{
	/**
	 * El estado se representa con un array de 16 posiciones. En cada una, está el valor de la carta. Si la carta se ha quitado hay un 0.
	 */
	private int[] tablero;
	
	/**
	 * Constructora de estado inicial.
	 */
	public JuegoQuince(){
		tablero=new int[]{10,10,4,7,6,7,1,10,4,7,6,10,4,6,10,1};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor.
	 * @param juego estado padre
	 * @param cos coste acumulado
	 * @param cam camino hasta este estado
	 */
	private JuegoQuince(JuegoQuince juego,int cos,String cam){
		tablero=new int[16];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=juego.tablero[i];
		profundidad=juego.profundidad+1;
		coste=juego.coste+cos;
		camino=juego.camino+cam;
	}
	
	/**
	 * Método que calcula una lista con los posibles candidatos del tablero que cumplen lo que diga 's', que representa el operador que se quiere aplicar. La
	 * lista se calcula para la carta que ocupa la posición p.
	 * @param s representación del operador que se quiere aplicar
	 * @param p posición de la carta para la que se calcula la lista de candidatos
	 * @return lista con las posiciones de las cartas que cumplirían lo que diga s.
	 */
	private Vector<Integer> puedo(String s,int p){
		Vector<Integer> v=new Vector<Integer>(0,1);
		int valor=tablero[p];
		if (s.equals("S2")){
			for (int i=0;i<tablero.length;i++){
				if (valor+tablero[i]==15 && i!=p)
					v.add(i);
			}
		}
		if (s.equals("S3")){
			for (int i=0;i<tablero.length;i++){
				for (int j=0;j<tablero.length;j++){
					if (p!=i && i!=j && valor+tablero[i]+tablero[j]==15){
						v.add(i);
						v.add(j);
					}
				}
			}
		}
		if (s.equals("QI")){
			for (int i=0;i<tablero.length;i++){
				if (valor==tablero[i] && i!=p)
					v.add(i);
			}
		}
		return v;
	}
	
	/**
	 * Operador que quita 2 cartas, dadas por la posición que ocupan
	 * @param i carta 1
	 * @param j carta 2
	 */
	private void Quita2(int i,int j){
		tablero[i]=0;
		tablero[j]=0;
		valorHeur=this.Heuristica();
	}
	
	/**
	 * Operador que quita 3 cartas, dadas por la posición que ocupan
	 * @param i carta 1
	 * @param j carta 2
	 * @param k carta 3
	 */
	private void Quita3(int i,int j,int k){
		tablero[i]=0;
		tablero[j]=0;
		tablero[k]=0;
		valorHeur=this.Heuristica();
	}
	
	private double Heuristica(){
		double d=0;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]!=0)
				d+=1;
		return d;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		JuegoQuince estado;
		Vector<Integer> v=new Vector<Integer>(0,1);
		int p=0;
		// buscar la primera carta que aún no se ha quitado del tablero
		while (tablero[p]==0 && p<tablero.length)
			p++;
		v=this.puedo("QI",p);
		if (!v.isEmpty()){
			for (int i=0;i<v.size();i++){
				estado=new JuegoQuince(this,2,"Quita iguales: "+p+" "+v.elementAt(i)+"\n");
				estado.Quita2(p,v.elementAt(i));
				sucesores.add(estado);
			}
		}
		v=this.puedo("S2",p);
		if (!v.isEmpty()){
			for (int i=0;i<v.size();i++){
				estado=new JuegoQuince(this,2,"Suma 2: "+p+" "+v.elementAt(i)+"\n");
				estado.Quita2(p,v.elementAt(i));
				sucesores.add(estado);
			}
		}
		v=this.puedo("S3",p);
		if (!v.isEmpty()){
			for (int i=0;i<v.size();i+=2){
				estado=new JuegoQuince(this,3,"Suma 3: "+p+" "+v.elementAt(i)+" "+v.elementAt(i+1)+"\n");
				estado.Quita3(p,v.elementAt(i),v.elementAt(i+1));
				sucesores.add(estado);
			}
		}
		return sucesores;
	}
	
	public boolean isGoal(){
		int ceros=0;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]==0)
				ceros++;
		return ceros>14;
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		JuegoQuince estado=(JuegoQuince)o;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]!=estado.tablero[i])
				return false;
		return true;
	}
	
	public String toString(){
		return "Juego quince\n["+tablero[0]+" "+tablero[1]+" "+tablero[2]+" "+tablero[3]+"]\n"+
				"["+tablero[4]+" "+tablero[5]+" "+tablero[6]+" "+tablero[7]+"]\n"+
				"["+tablero[8]+" "+tablero[9]+" "+tablero[10]+" "+tablero[11]+"]\n"+
				"["+tablero[12]+" "+tablero[13]+" "+tablero[14]+" "+tablero[15]+"]\n";
	}
	
}