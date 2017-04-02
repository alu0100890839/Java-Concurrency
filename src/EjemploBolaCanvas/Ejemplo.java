/**
 * Ejemplo para mostrar una de las utilidades de la concurrencia. En este caso el programa no tiene el funcionamiento esperado
 * de una aplicación interactiva, puesto que no responde bien a las acciones del usuario mientras procesa los cambios de la bola.
 *
 * Pablo Pastor Martín y Jorge Sierra Acosta
 * Programación de Aplicaciones Interactivas
 */

package EjemploBolaCanvas;

import java.awt.image.BufferStrategy;

import javax.swing.*;
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

/**
 * Clase para la pelota del ejemplo, contiene el tamaño y realiza las operaciones para cambiarlo
 */
class Pelota{
	private int tam = 15; // Tamaño de la pelota
	private boolean creciendo = true;

	/**
	 * Modifica el tamaño de la pelota de acuerdo a las dimensiones del panel que la contiene
	 * @param width Ancho del panel
	 * @param height Alto del panel
	 */
	public void modPelota(int width, int height){
		tam += creciendo ? 3 : -3;
		if(tam < 5) {
			creciendo = true;
		}
		if(tam > Math.min(width, height)) {
			creciendo = !creciendo;
		}
	}

	/**
	 * Getter del tamaño
	 * @return tamaño
	 */
	public int getTam() {
		return tam;
	}
}

/**
 * Panel que contiene la pelota del ejemplo
 */
class PanelPelota extends JPanel{
	Canvas canvas;
	private Pelota pelota;

	/**
	 * La pelota que contiene el panel será la especificada como argumento
	 * y se creará un canvas sobre el que se dibuja.
	 * @param pelota Pelota a contener por el panel
	 */
	public void add(Pelota pelota){
		this.pelota = pelota;
		this.canvas = new Canvas();
		add(canvas);
		canvas.createBufferStrategy(2);
	    canvas.setBackground(Color.WHITE);
	    canvas.setVisible(true);
	    canvas.setFocusable(false);
	}

	/**
	 * Método encargado de pintar el panel
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.pelota != null) {
			updateCanvas();
		}
	}

	/**
	 * Dibuja la bola en el canvas
	 */
	public void updateCanvas() {
		int tam = pelota.getTam();
		int width = getWidth();
		int height = getHeight();
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics g = bufferStrategy.getDrawGraphics();

		canvas.setSize(width, height);
		g.clearRect(0, 0, width, height);
		g.fillOval(getWidth() / 2 - tam / 2,getHeight() / 2 - tam / 2, tam, tam);
		bufferStrategy.show();
        g.dispose();
	}
}

/**
 * Clase para la ventana principal
 */
class FrameJuego extends JFrame{
	PanelPelota panelPelota;
	JPanel panelBotones;

	/**
	 * Constructor por defecto, pone los botones y los paneles
	 */
	public FrameJuego(){
		panelPelota = new PanelPelota();
		panelBotones = new JPanel();

		setBounds(600, 300, 400, 350);
		setTitle ("Rebotes");
		ponerBoton(panelBotones, "Empezar", new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				comienzaEjemplo();
			}
		});
		ponerBoton(panelBotones, "Salir", new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				System.exit(0);
			}
		});
		add(panelPelota, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}

	/**
	 * Añade un botón a su panel correspondiente, también indicado
	 * @param c Contenedor del botón
	 * @param titulo Título del botón
	 * @param oyente Oyente del botón
	 */
	private void ponerBoton(Container c, String titulo, ActionListener oyente){
		JButton boton = new JButton(titulo);
		c.add(boton);
		boton.addActionListener(oyente);
	}

	/**
	 * Arranca la ejecución de la animación: Crea la pelota y se realiza el bucle.
	 */
	public void comienzaEjemplo (){
		Pelota pelota = new Pelota();
		panelPelota.add(pelota);

		for (int i=1; i<=200; i++){
			try {
				Thread.sleep(20); // ~60 FPS
			} catch (InterruptedException e) {
				;
			}
			pelota.modPelota(panelPelota.getWidth(), panelPelota.getHeight());
			panelPelota.paint(panelPelota.getGraphics());
		}
	}
}
