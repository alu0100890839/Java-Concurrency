
public class LambdaRunnable {
	private static final int LOOP_COUNT = 1000;
	private static int sum = 0;
	
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
