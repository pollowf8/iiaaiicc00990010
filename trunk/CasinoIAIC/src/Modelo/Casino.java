package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import Controlador.Controlador;
import Modelo.Busquedas.*;
import Modelo.Juegos.*;
import Swing.Swing;

/**
 * @author jga
 *
 */
public class Casino {

	protected LinkedList<ObservadorPartida>observadores= new LinkedList<ObservadorPartida>();
	private int vidas;
	private ArrayList<Zona> zonas;
	private Juego juegos[];
	private Busqueda busquedas[];
	private FicheroTxt txt;
	private Stack<Zona> pila;
	
	/**
	 * Zona por la que se comienza a jugar
	 */
	private final int zonaInicial=0;
	
	/**
	 * Número máximo de búsquedas para resolver el juego
	 */
	private final int MAXbusquedas=8;
	
	/**
	 * Número máximo de juegos disponibles para resolver
	 */
	private final int MAXJuegos=5;
	
	/**
	 * Número máximo de zonas del casino
	 */
	private final int MAXZonas=this.MAXJuegos*this.MAXbusquedas;
	
	/**
	 * Número de salidas
	 */
	private final int MAXSalidas=3;
	
	public Casino(){
		this.vidas=29000;
		this.initJuegos();
		this.initBusquedas();
		this.initZonas();
		this.generaCaminos();
		txt= new FicheroTxt();
		pila=new Stack<Zona>();
	}
	
	private void initBusquedas() {
		this.busquedas=new Busqueda[this.MAXbusquedas];
		for (int i=0; i<this.MAXbusquedas; i++){
			switch (i){
			case 0:
				this.busquedas[i]=new AEstrella();
				break;
			case 1:
				this.busquedas[i]=new CosteUniforme();
				break;
			case 2:
				this.busquedas[i]=new EscaladaMaximaPendiente();
				break;
			case 3: 
				this.busquedas[i]=new PrimeroAnchura();
				break;
			case 4: 
				this.busquedas[i]=new PrimeroProfundidad();
				break;
			case 5: 
				this.busquedas[i]=new ProfundidadIterativa();
				break;
			case 6: 
				this.busquedas[i]=new ProfundidadLimitada();
				break;
			case 7: 
				this.busquedas[i]=new Voraz();
				break;
			}
		}
	}

	private void initJuegos() {
		this.juegos=new Juego[this.MAXJuegos];
		for (int i=0; i<this.MAXJuegos; i++){
			switch (i){
			case 0:
				this.juegos[i]=new Garrafas();
				break;
			case 1:
				this.juegos[i]=new Hanoi();
				break;
			case 2:
				this.juegos[i]=new LoboCabraCol();
				break;
			case 3: 
				this.juegos[i]=new Misioneros();
				break;
			case 4: 
				this.juegos[i]=new Puzzle8();
				break;
			}
		}
	}

	public boolean jugar(){		
		/*
		 * Pruebas de escritura en el log
		 */
		escribeEstado("fas");
		escribeEstado("asfasf");
		escribeEstado("fas");
		escribeEstado("asfasf");
		escribeEstado("fas");
		escribeEstado("asfasf");
		escribeEstado("fas");
		escribeEstado("asfasf");
		txt.escribeFichero("probando");
		txt.escribeFichero("probando2");
		txt.escribeFichero("probando3");
		txt.escribeFichero("probando4");
		
		
		return this.juegaZona(this.zonaInicial);
	}
	
	private boolean juegaZona(int zona) {
		// CASO BASE
		// La zona es salida
		Zona actual=this.zonas.get(zona);
		if (actual.isFin()) return true;
		// CASO RECURSIVO
		else {
			// Elegimos el hijo con mayor índice y que no ha sido visitado aún
			int i=0;
			while (i<actual.getNumHijos() && zonas.get(actual.getHijos().get(i)).isVisitada()){
				i++;
			}
			Zona siguiente;
			
			// CASO RECURSIVO 1: Todos los hijos visitados
			if (i==actual.getNumHijos()){
				// CASO RECURSIVO 1A: No quedan zonas a las que retroceder
				if (this.pila.peek()==null) return false;
				// CASO RECURSIVO 1B: Retrocedemos a la zona anterior
				else {
					siguiente=this.pila.pop();
					return this.juegaZona(siguiente.getIndice());
				}
			}

			// CASO RECURSIVO 2: Tenemos un hijo sin visitar. Ejecutamos su juego/busqueda
			siguiente=this.zonas.get(i);
			Juego juego=this.juegos[siguiente.getJuego()];
			Busqueda bus=this.busquedas[siguiente.getBusqueda()];
			Juego sol=bus.resuelve(juego);
			// CASO RECURSIVO 2A: Encuentra solución. Apilamos zona actual y jugamos siguiente.
			if (sol.isGoal()) {
				this.pila.push(actual);
				return this.juegaZona(siguiente.getIndice());
			} else {
			// CASO RECURSIVO 2B: No encuentra solución. Marcamos como visitada y jugamos de nuevo la actual.
				siguiente.setVisitada(true);
				return this.juegaZona(actual.getIndice());
			}
		}
	}

	/**
	 * Inicializa las zonas asignando aleatoriamente y sin repetir un juego y una búsqueda 
	 * a cada una. Por lo tanto se crean (MAXJuegos*MAXBusquedas) zonas.
	 */
	private void initZonas(){
		
		/* Array auxiliar que utilizamos para la asignación de juegos y zonas */
		/* De 0 a MAXZonas-1 (todas las combinaciones posibles) */
		boolean usadas[][]=new boolean[this.MAXJuegos][this.MAXbusquedas];
		for (int i=0; i<this.MAXJuegos; i++){
			for (int j=0; j<this.MAXbusquedas; j++){
				usadas[i][j]=false;
			}
		}
		
		/* Inicializamos la lista de zonas */
		zonas=new ArrayList<Zona>(this.MAXZonas);
		/* variables auxiliares 
		 * nJ: número de juego asignado
		 * nB: número de búsqueda asignada
		 * asignada: posición generada aleatoriamente entre 0 y MAXZonas-1
		 */
		int nJ, nB, asignada=0;
		/* Para cada zona */
		for (int i=0; i<this.MAXZonas; i++){
			/* Genera un número aleatorio del array usadas */
			Random r=new Random();
			asignada=r.nextInt(this.MAXZonas);
			nJ=asignada%this.MAXJuegos;
			nB=(int)asignada/this.MAXJuegos;
			/* Comprueba que no ha sido usada previamente */
			while (usadas[nJ][nB]){
				asignada=r.nextInt(this.MAXZonas);
				nJ=asignada%this.MAXJuegos;
				nB=(int)asignada/this.MAXJuegos;
			}
			/* asigna el juego y la búsqueda */
			this.zonas.add(new Zona(i,nJ,nB,0,0));
			/* marcado */
			usadas[nJ][nB]=true;
		}
		
		/* marcamos las tres últimas zonas como salida */
		for (int i=this.MAXZonas-this.MAXSalidas; i<this.MAXZonas; i++){
			this.zonas.get(i).setFin(true);
		} 
	}
	
	/**
	 * Genera un grafo conexo entre las zonas de una forma determinada.
	 */
	private void generaCaminos() {
		/*
		 * Rango inicial: primer 20%
		 * Genera 4 caminos hacia delante y uno a la zona siguiente.
		 */
		int finRango1=(int) (this.MAXZonas*0.2);
		int finRango2=(int) (this.MAXZonas*0.6)+finRango1-1;
		int nHijo;
		Zona aux;
		for (int i=0; i<finRango1; i++){
			Random r=new Random();
			nHijo=r.nextInt(this.MAXZonas-this.MAXSalidas-i-2)+i+2;
			aux=this.zonas.get(i);
			while (aux.getHijos().size()<4){
				if (!aux.getHijos().contains(nHijo)) aux.addHijo(nHijo);
				nHijo=r.nextInt(this.MAXZonas-this.MAXSalidas-i-2)+i+2;
			}
			aux.addHijo(i+1);			
		}
		
		/*
		 * Rango intermedio: 60%
		 * Genera 3 caminos hacia delante, uno hacia atrás y uno a la zona siguiente
		 */
		for (int i=finRango1; i<finRango2; i++){
			Random r=new Random();
			nHijo=r.nextInt(this.MAXZonas-this.MAXSalidas-i-2)+i+2;
			aux=this.zonas.get(i);
			while (aux.getHijos().size()<3){
				if (!aux.getHijos().contains(nHijo)) aux.addHijo(nHijo);
				nHijo=r.nextInt(this.MAXZonas-this.MAXSalidas-i-2)+i+2;
			}
			aux.addHijo(i+1);
			nHijo=r.nextInt(i);
			aux.addHijo(nHijo);
		}
		
		/*
		 * Rango final: último 20%
		 * Genera 3 caminos hacia atrás, 1 hacia delante y otro a la siguiente zona.
		 */
		for (int i=finRango2; i<this.MAXZonas-this.MAXSalidas; i++){
			Random r=new Random();
			nHijo=r.nextInt(i);
			aux=this.zonas.get(i);
			while (aux.getHijos().size()<3){
				if (!aux.getHijos().contains(nHijo)) aux.addHijo(nHijo);
				nHijo=r.nextInt(i);
			}
			aux.addHijo(i+1);
			nHijo=r.nextInt(this.MAXZonas-i-2)+i+2;
			aux.addHijo(nHijo);
		}
		
		/*
		 * ASIGNACIÓN DE SALIDAS
		 * Genera 2 caminos hacia cada salida en una zona aleatoria que se encuentre en la segunda mitad
		 */
		/*
		int zonaSal1, zonaSal2;
		for (int i=0; i<this.MAXSalidas; i++){
			Random r=new Random();
			zonaSal1=r.nextInt((int) (this.MAXZonas*0.5))+(int) (this.MAXZonas*0.5);
			zonaSal2=r.nextInt((int) (this.MAXZonas*0.5))+(int) (this.MAXZonas*0.5);
			this.zonas.get(zonaSal1).addHijo(this.MAXZonas-this.MAXSalidas+i);
			this.zonas.get(zonaSal2).addHijo(this.MAXZonas-this.MAXSalidas+i);
		}
		*/
		
		
	}

	public void mostrarLog() {
		txt.open();
	}
	
	/**
	 * Llama al método escribeEstado de cada uno de los observadores.
	 *
	 */	
	public void escribeEstado(String estado)
	{ 
			for (ObservadorPartida o: observadores)
				o.escribeEstado(estado);
	}
	
	/**
	 * 
	 * @param obspart Añade un observador a la lista de observadores,
	 * si este observador ya existia no lo introduce.
	 */
	
	public void addObserver(ObservadorPartida obspart)
	{
	   if (!observadores.contains(obspart))
	      if (observadores!=null)
		     observadores.add(obspart);
	}
	
	/**
	 * 
	 * @param obspart Elimina un observador de la lista de observadores.
	 */
	
	public void removeObserver(ObservadorPartida obspart)
	{   if (observadores!=null)
			observadores.remove(obspart);
	
	}
	
	public static void main(String[] args) 
	{
		Casino test=new Casino();
		System.out.println("Fin creación de casino");
	}

}
