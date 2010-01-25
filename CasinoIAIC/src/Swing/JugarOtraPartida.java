package Swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class JugarOtraPartida extends JFrame{

	private boolean eleccion;
	private boolean cancelado;
	
	public JugarOtraPartida() {		
		eleccion=crearVentanaElec();
	}
	
	
	/**
	 * 
	 * @return el tipo de jugador elegido por el usuario.
	 */
	private boolean crearVentanaElec(){
		String[] opciones = {"SI", "NO"};
		String j=null;
		j= (String)JOptionPane.showInputDialog(null, "Elija una opción:", "¿Desea entrar de nuevo en el Casino?", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
		cancelado=false;
		if (j==null){ 
			cancelado=true;
			this.setVisible(false);
			return false;
		}
		else{
			if (j.contentEquals("SI"))
			return true;
			else return false;
		}
	}

	/**
	 * @return la eleccion realizada por el jugador.
	 */
	public boolean getEleccion() {
		return eleccion;
	}

	/**
	 * @return cierto si hemos cancelado la elección del portaviones como flota central.
	 */
	public boolean getCancelado() {
		return cancelado;
	}
	
}
