package GuardedBlock;

import java.util.Random;

public class EjemploGuardedBlock {
	
	private volatile boolean aux;
	
	public static void main(String args[]) {
		
		EjemploGuardedBlock ejemplo = new EjemploGuardedBlock();
		
		Runnable espera = () -> {
			ejemplo.waitForSignal();
		};
		
		Runnable notificador = () -> {
			try {
				Thread.sleep(new Random().nextInt(2000) + 500);
			}
			catch(Exception e) {}
			ejemplo.notificar();
		};
		
		new Thread(espera).start();
		new Thread(notificador).start();
	}
	
	private void waitForSignal() {
		System.out.println("Hilo 1: Esperando por la señal...");
		long contador = 0;
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
	
	private synchronized void notificar() {
		aux = true;
		System.out.println("Hilo 2: Ya es true");
		//notifyAll();
	}
}
