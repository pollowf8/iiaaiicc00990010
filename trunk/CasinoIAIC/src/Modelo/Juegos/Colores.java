package Modelo.Juegos;

import java.util.Vector;

public class Colores extends Juego{
	private int[] tablero;
	
	public Colores(){
		tablero=new int[]{1,1,1,2,1,1,2,2,2};
		valorHeur=this.Heuristica();
		coste=0;
		profundidad=0;
		camino="";
	}
	
	private Colores(Colores color,int cos,String cam){
		tablero=new int[9];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=color.tablero[i];
		coste=color.coste+cos;
		profundidad=color.profundidad+1;
		camino=color.camino+cam;
	}
	
	private void cambia(int p){
		if (tablero[p]==1)
			tablero[p]=2;
		else
			tablero[p]=1;
		valorHeur=this.Heuristica();
	}
	
	// calcula el numero de pares adyacentes de colores iguales
	private double Heuristica(){
		double d=0;
		for (int i=0;i<tablero.length;i++){
			// adyacente izquierda
			if ((i>0 && i<3) || (i>3 && i<6) || (i>6))
				if (tablero[i]==tablero[i-1])
					d+=1;
			// adyacente arriba
			if (i>2)
				if (tablero[i]==tablero[i-3])
					d+=1;
			// adyacente derecha
			if ((i<2) || (i>2 && i<5) || (i>5 && i<8))
				if (tablero[i]==tablero[i+1])
					d+=1;
			// adyacente abajo
			if (i<6)
				if (tablero[i]==tablero[i+3])
					d+=1;
		}
		return d;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Colores estado;
		for (int i=0;i<tablero.length;i++){
			estado=new Colores(this,1,"Cambio color de "+i+"\n");
			estado.cambia(i);
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
		Colores estado=(Colores)o;
		for (int i=0;i<tablero.length;i++)
			if (tablero[i]!=estado.tablero[i])
				return false;
		return true;
	}
	
	public String toString(){
		String s="Colores\n";
		for (int i=0;i<tablero.length;i++){
			if (i==0 || i==3 || i==6)
				s+="[";
			s+=" "+tablero[i]+" ";
			if (i==2 || i==5 || i==8)
				s+="]\n";
		}
		return s;
	}
	
}