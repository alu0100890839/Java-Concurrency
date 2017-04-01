
public class MyThread extends Thread {
	private final static int THREADS = 10;
	private final int LOOP_COUNT = 100_000_000;
	private int sum;
	
	public static void main(String args[]) {
		MyThread[] threads = new MyThread[THREADS];
		
		for (int i = 0; i < THREADS; ++i) {
			threads[i] = new MyThread();
			threads[i].start();
		}
		System.out.println("Using threads!");
		System.out.println("Hello from main: " + Thread.currentThread().getName());
	}
	
	public MyThread() {
		super();
	}
	
	@Override
	public void run() {
		setSum(0);
		for (int i = 0; i < LOOP_COUNT; ++i) {
			incSum();
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
