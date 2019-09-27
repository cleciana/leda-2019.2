package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex == rightIndex || array.length <= 0) return;
		
		int meio = (leftIndex + rightIndex) / 2;
		sort(array, leftIndex, meio);
		sort(array, meio+1, rightIndex);
		
		mergeSort(array, leftIndex, meio, rightIndex);
	}

	@SuppressWarnings("unchecked")
	private void mergeSort(T[] numeros, int inicio, int meio, int fim) {
		
		Comparable<Object>[] helper = new Comparable[numeros.length];
        for (int i = inicio; i <= fim; i++) {
            helper[i] = (Comparable<Object>) numeros[i];
        }
        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {
            if (helper[i].compareTo(helper[j]) < 0) {
                numeros[k] = (T) helper[i];
                i++;
            } else {
                numeros[k] = (T) helper[j];
                j++;
            }
            k++;
        }
        // primeira metade não foi toda consumida: fazer append de todos
        // os elementos da primeira metade
        while (i <= meio) {
            numeros[k] = (T) helper[i];
            k+=1;
            i+=1;
        }
        // segunda metade não foi toda consumida: fazer append de todos
        // os elementos da segunda metade
        while (j <= fim) {
            numeros[k] = (T) helper[j];
            k+=1;
            j+=1;
        }
    }

}
