package Modelo.Juegos;

import java.util.Vector;

public class JuegoQuince extends Juego{
	private int[] tablero;
	
	public JuegoQuince(){
		tablero=new int[]{10,10,4,7,6,7,1,10,4,7,6,10,4,6,10,1};
		valorHeur=-1;
		coste=0;
		profundidad=0;
		camino="";
	}
	
	private JuegoQuince(JuegoQuince juego,int cos,String cam){
		tablero=new int[16];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=juego.tablero[i];
		profundidad=juego.profundidad+1;
		coste=juego.coste+cos;
		camino=juego.camino+cam;
	}
	
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
	
	private void Quita2(int i,int j){
		tablero[i]=0;
		tablero[j]=0;
	}
	
	private void Quita3(int i,int j,int k){
		tablero[i]=0;
		tablero[j]=0;
		tablero[k]=0;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		JuegoQuince estado;
		Vector<Integer> v=new Vector<Integer>(0,1);
		int p=0;
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
		return "["+tablero[0]+" "+tablero[1]+" "+tablero[2]+" "+tablero[3]+"]\n"+
				"["+tablero[4]+" "+tablero[5]+" "+tablero[6]+" "+tablero[7]+"]\n"+
				"["+tablero[8]+" "+tablero[9]+" "+tablero[10]+" "+tablero[11]+"]\n"+
				"["+tablero[12]+" "+tablero[13]+" "+tablero[14]+" "+tablero[15]+"]\n";
	}
	
}