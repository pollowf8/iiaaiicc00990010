package Modelo.Juegos;

import java.util.Vector;

public class Relojes extends Juego{
	private int r11h;
	private int r11l;
	private int r7h;
	private int r7l;
	
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
	
	private Relojes(Relojes reloj,int cos,String cam){
		r11h=reloj.r11h;
		r11l=reloj.r11l;
		r7h=reloj.r7h;
		r7l=reloj.r7l;
		profundidad=reloj.profundidad+1;
		coste=reloj.coste+cos;
		camino=reloj.camino+cam;
	}
	
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
	
	private void R11(){
		r11h=11;
		r11l=0;
	}
	
	private void R7(){
		r7h=7;
		r7l=0;
	}
	
	private void R11R7(){
		r11h=11;
		r11l=0;
		r7h=7;
		r7l=0;
	}
	
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
		return "Reloj 11 restante: "+r11h+" Reloj 7 restante: "+r7h+"\n";
	}
	
}