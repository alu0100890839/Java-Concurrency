/**
 * Este ejemplo ilustra como dos hilos intentando acceder al mismo recurso
 * pueden quedarse bloqueados indefinidamente.
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

package Deadlock;

/**
 * Clase que nos servirá para el ejemplo
 */
public class Deadlock {
	public static void main(String[] args) {
		final String resource1 = "Resource one"; // Recurso 1
		final String resource2 = "Resource two"; // Recurso 2

		// t1 tries to lock resource1 then resource2
		Thread t1 = new Thread() { // Primer hilo
			public void run() {
				synchronized (resource1) { // Haz un lock del recurso 1 o espera a que esté disponible
					System.out.println("Thread 1: locked resource 1");
					try { Thread.sleep(100);} catch (Exception e) {}
					System.out.println("Thread 1: Waiting for resource 2...");
					synchronized (resource2) { // Haz un lock del recurso 2 o espera a que esté disponible
						System.out.println("Thread 1: locked resource 2");
					}
				}
			}
		};

		// t2 tries to lock resource2 then resource1
		Thread t2 = new Thread() {
			public void run() {
				synchronized (resource2) { // Haz un lock del recurso 2 o espera a que esté disponible
					System.out.println("Thread 2: locked resource 2");
					try { Thread.sleep(100);} catch (Exception e) {}
					System.out.println("Thread 1: Waiting for resource 1...");
					synchronized (resource1) { // Haz un lock del recurso 1 o espera a que esté disponible
						System.out.println("Thread 2: locked resource 1");
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
}
