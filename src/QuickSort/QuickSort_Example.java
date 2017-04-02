/**
 * Este ejemplo ilustra las ventajas de utilizar dos hilos en quicksort.
 *
 * Programación de Aplicaciones Interactivas
 * Pablo Pastor Martín y Jorge Sierra Acosta
 */

package QuickSort;

/**
 * Clase que nos servirá para el ejemplo
 */
public class QuickSort_Example {
	private final static int TESTS = 3;				// Número de ordenaciones para cada prueba
	private final static int ARR_SIZE = 10_000_000; // Cantidad de elementos a ordenar
	private static long startTime;					// Tiempo en el que comenzó a ordenar (Usado para medir el tiempo)

    public static void main(String args[]) {
    	QuickSort sorter = new QuickSort(); // Nueva instancia del quicksort.

    	startTimeMeasure();
    	for (int i = 0; i < TESTS; ++i) {
        	int[] array = randomArray(ARR_SIZE);
            sorter.sort(array, false); // Ordena de la forma tradicional
    	}
        endTimeMeasure();

    	startTimeMeasure();
    	for (int i = 0; i < TESTS; ++i) {
        	int[] array = randomArray(ARR_SIZE);
            sorter.sort(array, true); // Ordena utilizando dos hilos
    	}
        endTimeMeasure();
    }

	/**
	 * Genera un array aleatorio
	 */
    public static int[] randomArray(int size) {
    	int[] array = new int[size];

    	for (int i = 0; i < size; ++i)
    		array[i] = (int) (Math.random() * ARR_SIZE);
    	return array;
    }

	/**
	 * Empieza a medir el tiempo
	 */
    public static void startTimeMeasure() {
    	startTime = System.nanoTime();
    }

	/**
	 * Finaliza la medición de tiempo
	 */
    public static void endTimeMeasure() {
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.println("Average time per sort: " + (estimatedTime * 1e-6 / TESTS) + " ms");
    }
}
