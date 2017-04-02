/**
 * Este ejemplo ilustra muy bien un ejemplo de inconsistencia de la memoria entre hilos, así como un ejemplo de coordinación entre ellos
 * mediante Guarded Blocks.
 */

package GuardedBlock;

import java.util.Random;

/**
 * Clase que nos servirá para el ejemplo
 */
public class EjemploGuardedBlock {
	
	private volatile boolean aux;		// Variable booleana que sirve como condición. Deberá ser volatile para evitar inconsistencia (Debido a las optimizaciones de Java)
	
	public static void main(String args[]) {
		
		EjemploGuardedBlock ejemplo = new EjemploGuardedBlock();
		
		// Creamos los Runnables mediante expresiones Lambda
		
		Runnable espera = () -> {
			// Esperará por la señal
			ejemplo.waitForSignal();
		};
		
		Runnable notificador = () -> {
			// Esperará un tiempo aleatorio y hará que la condición por la que el otro espera se cumpla
			try {
				Thread.sleep(new Random().nextInt(2000) + 500);
			}
			catch(Exception e) {}
			ejemplo.notificar();
		};
		
		// Arrancamos los hilos
		new Thread(espera).start();
		new Thread(notificador).start();
	}
	
	/**
	 * Método para esperar por la señal.
	 * Se puede hacer un bucle infinito hasta que se cumpla la condición (Ineficiente) o pausar y esperar por una señal.Imprime las iteraciones hechas
	 */
	private void waitForSignal() {
		System.out.println("Hilo 1: Esperando por la señal...");
		long contador = 0;
		// Bucle de espera
		while(!aux) {
			/*
			try {
				wait();
			} catch (InterruptedException e) {
			}
			*/
			contador++;
		}
		System.out.println("Hilo 1: Ya puedo seguir tras " + contador + " iteraciones");
	}
	
	/**
	 * Método que hace que se cumpla la confición que espera el otro hilo y manda una señal para avisar.
	 */
	private synchronized void notificar() {
		aux = true;
		System.out.println("Hilo 2: Ya es true");
		//notifyAll();
	}
}
