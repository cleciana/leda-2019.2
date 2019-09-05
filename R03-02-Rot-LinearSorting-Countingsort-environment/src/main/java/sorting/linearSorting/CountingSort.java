package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0)	return;
		
		int tamanho = max(array, leftIndex, rightIndex);
		int[] contagem = new int[tamanho+1];
		
		// contar frequencia
		for (int i = leftIndex; i <= rightIndex; i++) {
			contagem[array[i]]++;
		}
		
		int soma = 0;	
		// calcular cumulativa
		for(int j = leftIndex; j <= tamanho; j++) {
			soma += contagem[j];
			contagem[j] = soma;
		}
		
		Integer[] b = new Integer[soma];
		// percorrer array de contagem, ordenando em b
		for (int k = soma - 1; k >= 0; k--) {
			b[contagem[array[k]] - 1] = array[k]; 
			contagem[array[k]] -= 1;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = b[i];
		}
	}
	
	private int max(Integer[] a, int leftIndex, int rightIndex) {
		
		int i = leftIndex;
		int maximo = a[leftIndex];
		
		while (i <= rightIndex) {
			if (maximo < a[i]) {
				maximo = a[i];
			}
			i++;
		}
		return maximo;
	}
}
