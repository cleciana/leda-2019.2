package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean trocou;
		
		for (int i = leftIndex; i < rightIndex; i++) {
			trocou = false;
			
			for (int j = i+1; j < rightIndex - i + 1; j++) {
				if (array[i].compareTo(array[j]) == 1) {
					Util.swap(array, i, j);
					trocou = true;
				}
			}
			if (!trocou) {
				return;
			}
		}
	}
}
