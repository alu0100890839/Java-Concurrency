package Suma;

public class SumaEjemplo {
	public static void main (String args[]) throws InterruptedException {
		Counter counter = new Counter();
		
		Thread hilo1 = new Thread(new Hilo(counter));
		Thread hilo2 = new Thread(new Hilo(counter));
		hilo1.start();
		hilo2.start();
		
		hilo1.join();
		hilo2.join();
		
		System.out.println(counter.getNumero());
	}
	
	
}

class Hilo implements Runnable{
	private Counter counter;
	public Hilo(Counter counter) {
		this.counter = counter;
	}
	
	public void run() {
		for(int i = 0; i < 10000; i++) {
			counter.siguiente();
		}
	}
}

class Counter{
	private int numero = 0;
	public void siguiente() {
		numero++;
	}
	
	public int getNumero() {
		return numero;
	}
}