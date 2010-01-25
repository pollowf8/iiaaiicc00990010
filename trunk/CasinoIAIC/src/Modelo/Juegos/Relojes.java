package Modelo.Juegos;

import java.util.Vector;

/**
 * Juego de los relojes. Disponemos de 2 relojes de arena, de 11 y 7 minutos respectivamente. Como operaciones, se puede dar la vuelta a un reloj, o dar la vuelta
 * a los dos simultaneamente, hasta que se agote uno de los 2. El objetivo es tener 3 minutos en uno de los relojes.
 * @author Pablo Acevedo, Alberto Díez, Jorge Guirado
 *
 */
public class Relojes extends Juego{
	/**
	 * Parte alta del reloj de 11 minutos
	 */
	private int r11h;
	/**
	 * Parte baja del reloj de 11 minutos
	 */
	private int r11l;
	/**
	 * Parte alta del reloj de 7 minutos
	 */
	private int r7h;
	/**
	 * Parte baja del reloj de 7 minutos
	 */
	private int r7l;
	
	/**
	 * Constructora de estado inicial
	 */
	public Relojes(){
		r11h=0;
		r11l=11;
		r7h=0;
		r7l=7;
		valorHeur=-1;
		coste=0;
		profundidad=0;
		camino="";
	}
	
	/**
	 * Constructora de sucesor
	 * @param reloj estado padre
	 * @param cos coste acumulado
	 * @param cam camino hasta el estado
	 */
	private Relojes(Relojes reloj,int cos,String cam){
		r11h=reloj.r11h;
		r11l=reloj.r11l;
		r7h=reloj.r7h;
		r7l=reloj.r7l;
		valorHeur=reloj.valorHeur;
		profundidad=reloj.profundidad+1;
		coste=reloj.coste+cos;
		camino=reloj.camino+cam;
	}
	
	/**
	 * Método que calcula si es posible aplicar el operador definido por 's'. Como restricción, sólo se puede dar la vuelta a un reloj cuando está vacío. Igual
	 * en el caso de darle la vuelta a los dos relojes a la vez, tienen que estar los dos vacíos.
	 * @param s operador que se quiere aplicar
	 * @return true si es posible, false en caso contrario
	 */
	private boolean puedo(String s){
		if (s.equals("R11")){
			if (r11h==0)
				return true;
		}
		if (s.equals("R7")){
			if (r7h==0)
				return true;
		}
		if (s.equals("R11R7")){
			if (r11h==0 && r7h==0)
				return true;
		}
		return false;
	}
	
	/**
	 * Operador que le da la vuelta al reloj de 11 minutos
	 */
	private void R11(){
		r11h=11;
		r11l=0;
	}
	
	/**
	 * Operador que le da la vuelta al reloj de 7 minutos
	 */
	private void R7(){
		r7h=7;
		r7l=0;
	}
	
	/**
	 * Operador que le da la vuelta a los dos relojes
	 */
	private void R11R7(){
		r11h=11;
		r11l=0;
		r7h=7;
		r7l=0;
	}
	
	/**
	 * Método que vacía el tiempo de los relojes, cuando uno de los dos se agota se para
	 */
	private void vaciaTiempo(){
		while (r11h>0 && r7h>0){
			r11h--;
			r11l++;
			r7h--;
			r7l++;
		}
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Relojes estado;
		// antes de poder aplicar operadores hay que dejar que se agote el tiempo
		this.vaciaTiempo();
		if (this.puedo("R11R7")){
			estado=new Relojes(this,2,"Dar la vuelta a R11 y R7\n");
			estado.R11R7();
			sucesores.add(estado);
		}
		if (this.puedo("R11")){
			estado=new Relojes(this,1,"R11 acabó,darle la vuelta\n");
			estado.R11();
			sucesores.add(estado);
		}
		if (this.puedo("R7")){
			estado=new Relojes(this,1,"R7 acabó,darle la vuelta\n");
			estado.R7();
			sucesores.add(estado);
		}
		return sucesores;
	}
	
	public boolean isGoal(){
		return r11h==3||r7h==3;
	}
	
	public boolean equals(Object o){
		if (this==o)
			return true;
		if ((o==null) || (this.getClass()!=o.getClass()))
			return false;
		Relojes estado=(Relojes)o;
		return (r11h==estado.r11h && r11l==estado.r11l && r7h==estado.r7h && r7l==estado.r7l);
	}
	
	public String toString(){
		return "Relojes\nReloj 11 restante: "+r11h+" Reloj 7 restante: "+r7h+"\n";
	}
	
}