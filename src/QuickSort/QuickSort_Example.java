
public class QuickSort_Example {
	private final static int TESTS = 3;
	private final static int ARR_SIZE = 10_000_000;
	private static long startTime;
    
    public static void main(String args[]) {
    	QuickSort sorter = new QuickSort();
        
    	startTimeMeasure();
    	for (int i = 0; i < TESTS; ++i) {
        	int[] array = randomArray(ARR_SIZE);
            sorter.sort(array, false);
    	}
        endTimeMeasure();
        
    	startTimeMeasure();
    	for (int i = 0; i < TESTS; ++i) {
        	int[] array = randomArray(ARR_SIZE);
            sorter.sort(array, true);
    	}
        endTimeMeasure();
    }
    
    public static int[] randomArray(int size) {
    	int[] array = new int[size];
    	
    	for (int i = 0; i < size; ++i) 
    		array[i] = (int) (Math.random() * ARR_SIZE);
    	return array;
    }
    
    public static void startTimeMeasure() {
    	startTime = System.nanoTime();  
    }
    
    public static void endTimeMeasure() {
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.println("Average time per sort: " + (estimatedTime * 1e-6 / TESTS) + " ms");
    }
}
