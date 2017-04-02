/**
 * Aquí tenemos un pequeño ejemplo de intercalado de hilos. Puesto que 20_000 incrementos de 1 unidad cada uno, producen un resultado menor
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

package Suma;

/**
 * Clase que contiene el main
 */
public class SumaEjemplo {
	/**
	 * Función main, genera un contador y dos hilos que lo incrementan.
	 */
	public static void main (String args[]) throws InterruptedException {
		Counter counter = new Counter();

		Thread hilo1 = new Thread(new Hilo(counter)); // Creamos el hilo a partir de instancias de Runnable
		Thread hilo2 = new Thread(new Hilo(counter));
		hilo1.start();
		hilo2.start();

		hilo1.join();	// Esperamos a que los hilos terminen
		hilo2.join();

		System.out.println(counter.getNumero());
	}
}

/**
 * Clase que implementa Runnable y que por tanto tiene el código a ejecutar por el bucle.
 */
class Hilo implements Runnable{
	private Counter counter;	// Instancia del contador a usar

	/**
	 * Constructor a partir del contador
	 * @param counter Contador a usar
	 */
	public Hilo(Counter counter) {
		this.counter = counter;
	}

	/**
	 * Método a ejecutar por el hilo (10_000 incrementos de 1 unidad)
	 */
	public void run() {
		for(int i = 0; i < 10000; i++) {
			counter.siguiente();
		}
	}
}

/**
 * Clase para el contador que usarán los hilos.
 * Para arreglar el problema bastará con hacer el método siguiente sincronizado.
 */
class Counter{
	private int numero = 0;
	public void siguiente() {
		numero++;
	}

	public int getNumero() {
		return numero;
	}
}
