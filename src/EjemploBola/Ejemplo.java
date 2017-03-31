/**
 * Ejemplo para mostrar una de las utilidades de la concurrencia
 */

package EjemploBola;

import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase para la ejecución del ejemplo
 */
class Ejemplo {
	public static void main(String args[]) {
		FrameJuego frame = new FrameJuego();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Pelota{
	private int tam = 15;
	
	private boolean creciendo = true;
	
	public void modPelota(int width, int height){
		if(creciendo){
			tam += 3;
		}
		else {
			tam -= 3;
			if(tam < 5) {
				creciendo = true;
			}
		}
		if(tam > Math.min(width, height)) {
			creciendo = !creciendo;
		}
	}
	
	public int getTam() {
		return tam;
	}
	
}

class PanelPelota extends JPanel{
	private Pelota pelota;
	public void add(Pelota pelota){
		this.pelota = pelota;
		setDoubleBuffered(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.pelota != null) {
			int tam = pelota.getTam();
			Graphics2D g2=(Graphics2D)g;
			g2.fill(new Ellipse2D.Double(getWidth() / 2 - tam / 2,getHeight() / 2 - tam / 2, tam, tam));
		}
	}
}

/**
 * Clase para la ventana principal
 */
class FrameJuego extends JFrame{
	PanelPelota panelPelota;
	JPanel panelBotones;
	
	public FrameJuego(){
		setBounds(600,300,400,350);
		setTitle ("Rebotes");
		
		panelPelota = new PanelPelota();
		add(panelPelota, BorderLayout.CENTER);
		
		panelBotones = new JPanel();
		
		ponerBoton(panelBotones, "Empezar", new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				comienza_el_juego();
			}
		});
		
		ponerBoton(panelBotones, "Salir", new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				System.exit(0);
			}
		});
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	private void ponerBoton(Container c, String titulo, ActionListener oyente){
		JButton boton=new JButton(titulo);
		c.add(boton);
		boton.addActionListener(oyente);
	}
	
	public void comienza_el_juego (){
		Pelota pelota = new Pelota();
		panelPelota.add(pelota);
		
		for (int i=1; i<=200; i++){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			pelota.modPelota(panelPelota.getWidth(), panelPelota.getHeight());
			panelPelota.paint(panelPelota.getGraphics());
		}
	}
}
