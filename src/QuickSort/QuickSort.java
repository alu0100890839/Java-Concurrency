/**
 * Esta clase implementa un quicksort con métodos para funcionar en uno o dos hilos
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

 package QuickSort;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSort {
	private AtomicIntegerArray atomicArray; // Array atómico para evitar problemas cuando usamos dos hilos
	private int[] array;					// Array normal para cuando usamos un hilo

	/**
	 * Ordena los elementos del array utilizando uno de los métodos
	 * @param inputArr array a ordenar
     * @param concurrent utiliza o no concurrencia (dos hilos)
	 */
    public void sort(int[] inputArr, boolean concurrent) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        this.atomicArray = new AtomicIntegerArray(inputArr);
        if (concurrent)
        	quickSortConcurrent(0, inputArr.length - 1); // Con dos hilos
        else
        	quickSort(0, inputArr.length - 1); // Con un hilo
    }

	/**
	 * Método quicksort, 1 hilo.
	 * @param lowerIndex índice inferior del array
     * @param higherIndex índice superior del array
	 */
    private void quickSort(int lowerIndex, int higherIndex) {
    	int i = lowerIndex;
    	int j = higherIndex;
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }

        // Llama alos métodos de quicksort para la parte superior e inferior.
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

	/**
	 * Método quicksort, 2 hilos.
	 * @param lowerIndex índice inferior del array
     * @param higherIndex índice superior del array
	 */
    private void quickSortConcurrent(int lowerIndex, int higherIndex) {
       int i = lowerIndex;
       int j = higherIndex;
       int pivot = atomicArray.get(lowerIndex+(higherIndex-lowerIndex)/2);

       while (i <= j) {
           while (atomicArray.get(i) < pivot) {
               i++;
           }
           while (atomicArray.get(j) > pivot) {
               j--;
           }
           if (i <= j) {
               exchangeNumbers(i, j);
               i++;
               j--;
           }
       }

	   // Crea una tarea del tipo Runnable con la ordenación de la parte inferior
	   // con el quicksort estándar.
		final int jValue = j;
		Runnable task = () -> {
		if (lowerIndex < jValue)
		    quickSort(lowerIndex, jValue);
		};

		// Crea el objeto hilo con la tarea
	   	Thread task_lower = new Thread(task);

		// Comienza la tarea
	   	task_lower.start();

		// Ordena la parte superior de forma estándar.
        if (i < higherIndex)
            quickSort(i, higherIndex);

		// Espera a que acabe la parte inferior
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
