package LambdaRunnable;

public class LambdaRunnable {
	private final static int THREADS = 10;
	private static final int LOOP_COUNT = 100_000_000;
	private int sum;
	
	public static void main(String args[]) {
		Thread[] threads = new Thread[THREADS];
		
		System.out.println("Using Lambdas");
		System.out.println("Hello from main: " + Thread.currentThread().getName());
		for (int i = 0; i < THREADS; ++i) {
			LambdaRunnable aux = new LambdaRunnable();
			Runnable task = () -> {
				aux.setSum(0);
				for (int j = 0; j < LOOP_COUNT; j++) {
					aux.incSum();
				}
				System.out.println("Hello from main: " + Thread.currentThread().getName());
			};
			threads[i] = new Thread(task);
			threads[i].start();
		}
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
