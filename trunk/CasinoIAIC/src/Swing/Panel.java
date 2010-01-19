package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controlador.Controlador;
import Modelo.ObservadorPartida;



public class Panel extends JPanel implements ObservadorPartida{

	private JButton inicio;
	private JButton mostrarLog;
	private JButton cuadroTexto;
	private JTextArea texto;
	private ImageIcon fotoCasino, fotoDocs;
	private JScrollPane scroll;
	private String desarrollo;

	
	public Panel(){
		
		fotoCasino=new ImageIcon(("casino.jpg"));
		fotoDocs=new ImageIcon(("Docs.jpg"));
		this.setLayout(new GridLayout(0,3));
		//INICIO
		inicio=new JButton();
		inicio.addActionListener(new Pulsacion());
		inicio.setBackground(Color.LIGHT_GRAY);
		inicio.setIcon(fotoCasino);
		inicio.setName("1");
		this.add(inicio);
		//MOSTRAR LOG
		mostrarLog=new JButton();
		mostrarLog.addActionListener(new Pulsacion());
		mostrarLog.setBackground(Color.LIGHT_GRAY);			
		mostrarLog.setIcon(fotoDocs);
		mostrarLog.setName("2");
		this.add(mostrarLog);
		//TEXTO
		texto= new JTextArea();
		texto.setEditable(false);
		texto.setBackground(Color.WHITE);
		//SCROLL
		scroll = new JScrollPane(texto);
		//CUADRO DE TEXTO
		cuadroTexto=new JButton();
		cuadroTexto.setSize(300, 500);
		cuadroTexto.addActionListener(new Pulsacion());
		cuadroTexto.setBackground(Color.WHITE);	
		cuadroTexto.add(scroll);
		this.add(cuadroTexto);
		cuadroTexto.setEnabled(false);		
		desarrollo="";
		
		//initComponent();
	}

	
	/**
	 * Activamos el ratón para su uso durante el juego.
	 *
	 */
	/*public void initComponent(){
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				if (e.getButton()==MouseEvent.BUTTON1){
					//Controlador.getInstance()
				}
			}
		}
		);
	}*/
		
		
	/**
	 * 
	 * Implementamos la clase anonima encargada de obtener la 
	 * columna pulsada para pasarsela al controlador.
	 *
	 */	
	class Pulsacion implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton bot1=new JButton();
			bot1=(JButton)e.getSource();
			Scanner sc=new Scanner(bot1.getName());
			int opcion=sc.nextInt();
			Controlador.getInstance().ejecutaOpcion(opcion);
		}		
	}


	public void escribeEstado(String estado) {
		if (desarrollo.isEmpty())
			desarrollo=estado;
		else desarrollo=desarrollo+"\n"+estado;
		texto.setText(desarrollo);
		if (inicio.isEnabled())
			desactivarBotonInicio();
	}
	
	private void desactivarBotonInicio(){
		inicio.setEnabled(false);
	}


	
}
