
public class InterruptedMyThread extends Thread {
	private final int LOOP_COUNT = 100_000_000;
	private int sum;
	
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
	
	@Override
	public void run() {
		setSum(0);
		for (int i = 0; i < LOOP_COUNT; ++i) {
			incSum();
			if (Thread.interrupted())
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
