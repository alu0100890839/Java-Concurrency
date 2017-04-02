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
	private static final int LOOP_COUNT = 100_000_000; // Cada hilo ejecutará este número de sumas
	private int sum;							// Contiene el valor de la suma

	/**
	 * Función main, crea y ejecuta los hilos.
	 * @param args
	 */
	public static void main(String args[]) {
		LambdaRunnable aux = new LambdaRunnable();
		Runnable task = () -> {
			// Not permited: someVariable = 1;
			aux.setSum(0);
			for (int i = 0; i < LOOP_COUNT; ++i)
				aux.siguiente();
			System.out.println("Hello from main: " + aux.getSum());
		};

		Thread thread = new Thread(task);
		thread.start();
	}
	
	public int getSum() {
		return sum;
	}
	
	public void siguiente() {
		setSum(getSum() +1);
	}
	
	public void setSum(int aux) {
		sum = aux;
	}
}
