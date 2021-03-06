/**
 * Este ejemplo ilustra el funcionamiento básico de las interrupciones en un hilo.
 * Genera dos hilos, uno de ellos soporta la interrupción.
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

package InterruptThread;

public class InterruptedMyThread extends Thread {
	private final int LOOP_COUNT = 100_000_000; // Cada hilo ejecutará este número de sumas
	private int sum;							// Contiene el valor de la suma

	/**
	 * Función main, crea y ejecuta los hilos. El primero sin soportar interrupción
	 * el segundo soportándola.
	 * @param args
	 */
	public static void main(String args[]) throws InterruptedException {
		MyThread normal = new MyThread();
		normal.start();
		normal.interrupt();
		System.out.println("Hello from main");
		normal.join();

		InterruptedMyThread interrupted = new InterruptedMyThread();
		interrupted.start();
		Thread.sleep(10);
		interrupted.interrupt();
		System.out.println("Hello from main");
	}

	public InterruptedMyThread() {
		super();
	}

	/**
	 * Sobreescribimos el método run con el código a ejecutar
	 */
	@Override
	public void run() {
		setSum(0);
		for (int i = 0; i < LOOP_COUNT; ++i) {
			incSum();
			if (Thread.interrupted()) // Si es interrupido, detén la suma.
				break;
		}
		System.out.println("Hello from thread; sum = " + sum + ": " + Thread.currentThread().getName());
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void incSum() {
		setSum(getSum() + 1);
	}
}
