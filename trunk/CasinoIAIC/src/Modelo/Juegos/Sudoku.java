package Modelo.Juegos;

import java.util.Vector;

public class Sudoku extends Juego{
	private int[] tablero;
	
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
		valorHeur=-1;
		coste=0;
		profundidad=0;
		camino="";
	}
	
	private Sudoku(Sudoku juego,int cos,String cam){
		tablero=new int[81];
		for (int i=0;i<tablero.length;i++)
			tablero[i]=juego.tablero[i];
		profundidad=juego.profundidad+1;
		coste=juego.coste+cos;
		camino=juego.camino+cam;
	}
	
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
	
	private void ponValor(int p,int i){
		tablero[p]=i;
	}
	
	public Vector<Juego> expandir(){
		Vector<Juego> sucesores=new Vector<Juego>(0,1);
		Sudoku estado;
		int p=0;
		while (tablero[p]!=0 && p<tablero.length)
			p++;
		System.out.println("p: "+p);
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
		String s="";
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