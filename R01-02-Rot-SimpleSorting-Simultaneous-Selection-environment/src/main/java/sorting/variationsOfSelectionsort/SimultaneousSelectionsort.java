package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int minIndex;
		int maxIndex;

		for (int i = leftIndex; i < rightIndex; i++) {
			if (i > (leftIndex + rightIndex) / 2) return ;
			
			minIndex = i;
			maxIndex = i;
			for (int j = i + 1; j <= rightIndex - i; j++) {

				if (array[minIndex].compareTo(array[j]) > 0) {
					minIndex = j;
				}
			}
			Util.swap(array, minIndex, i);
			
			for (int j = i + 1; j <= rightIndex - i; j++) {

				if (array[maxIndex].compareTo(array[j]) < 0) {
					maxIndex = j;
				}
			}
			Util.swap(array, maxIndex, rightIndex - i);
		}
	}
}
