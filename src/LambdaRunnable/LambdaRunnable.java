/**
 * Este ejemplo ilustra el funcionamiento básico de un Runnable usando una
 * expresión lambda.
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

package LambdaRunnable;

/**
 * Clase que nos servirá para el ejemplo
 */
public class LambdaRunnable {
	private final int LOOP_COUNT = 100_000_000; // Cada hilo ejecutará este número de sumas
	private int sum;							// Contiene el valor de la suma

	/**
	 * Función main, crea y ejecuta los hilos.
	 * @param args
	 */
	public static void main(String args[]) {
		int someVariable = 0;

		Runnable task = () -> {
			// Not permited: someVariable = 1;
			sum = 0;
			for (int i = 0; i < LOOP_COUNT; ++i)
				sum++;
			System.out.println(sum);
		};

		Thread thread = new Thread(task);
		thread.start();
	}
}
