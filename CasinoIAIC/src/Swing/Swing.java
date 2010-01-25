package Swing;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import Controlador.Controlador;



@SuppressWarnings("serial")
public class Swing extends JFrame{
	
	
	private Panel panel;

	public Swing(){}

	public boolean nuevaPartida() {
		try{
			if (panel.fin()){
				this.remove(panel);
				panel.removeAll();
				panel=null;
				return true;
			}
			return panel.fin();
		}catch (NullPointerException e){return true;}
	}

	public void creaVentana() {
		this.setTitle("CASINO MICROMUNDOS FANTASMA");
		setSize(1000,450);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel=new Panel();
		this.add(panel,BorderLayout.CENTER);
		Controlador.getInstance().añadirObs(panel);
		setVisible(true);
	}
	

}
