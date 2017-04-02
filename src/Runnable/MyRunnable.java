package Runnable;

public class MyRunnable implements Runnable {
	private final static int THREADS = 10;
	private final int LOOP_COUNT = 100_000;
	private int sum;
	
	public static void main(String args[]) {
		MyRunnable[] runnables = new MyRunnable[THREADS];
		Thread[] threads = new Thread[THREADS];
		
		for (int i = 0; i < THREADS; ++i) {
			runnables[i] = new MyRunnable();
			threads[i] = new Thread(runnables[i]);
			threads[i].start();
		}
		System.out.println("Using runnables!");
		System.out.println("Hello from main: " + Thread.currentThread().getName());
	}
	
	@Override
	public void run() {
		setSum(0);
		for (int i = 0; i < LOOP_COUNT; ++i) {
			incSum();
		}
		System.out.println("Hello from thread: " + Thread.currentThread().getName());
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
