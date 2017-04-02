package QuickSort;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSort {
	private AtomicIntegerArray atomicArray;
	private int[] array;
 
    public void sort(int[] inputArr, boolean concurrent) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        this.atomicArray = new AtomicIntegerArray(inputArr);
        if (concurrent)
        	quickSortConcurrent(0, inputArr.length - 1);
        else 
        	quickSort(0, inputArr.length - 1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
        
    	int i = lowerIndex;
    	int j = higherIndex;
    	
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
    
    private void quickSortConcurrent(int lowerIndex, int higherIndex) {
       int i = lowerIndex;
       int j = higherIndex;
       // calculate pivot number, I am taking pivot as middle index number
       int pivot = atomicArray.get(lowerIndex+(higherIndex-lowerIndex)/2);
       // Divide into two arrays
       while (i <= j) {
           /**
            * In each iteration, we will identify a number from left side which
            * is greater then the pivot value, and also we will identify a number
            * from right side which is less then the pivot value. Once the search
            * is done, then we exchange both numbers.
            */
           while (atomicArray.get(i) < pivot) {
               i++;
           }
           while (atomicArray.get(j) > pivot) {
               j--;
           }
           if (i <= j) {
               exchangeNumbers(i, j);
               //move index to next position on both sides
               i++;
               j--;
           }
       }
       // call quickSort() method recursively

       
        final int jValue = j;
	   	Runnable task = () -> {
	        if (lowerIndex < jValue)
	            quickSort(lowerIndex, jValue);
	   	};
	   	
	   	Thread task_lower = new Thread(task);
	   	
	   	task_lower.start();
        if (i < higherIndex)
            quickSort(i, higherIndex);
        try {
			task_lower.join();
		} catch (InterruptedException e) {}
    }
 
    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
