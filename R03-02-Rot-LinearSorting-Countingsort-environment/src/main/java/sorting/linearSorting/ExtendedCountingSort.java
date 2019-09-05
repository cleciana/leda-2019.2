package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 *  - Alocar o tamanho minimo possivel para o array de contadores (C) 
 *  - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) return;
		
		int minimo = array[leftIndex], maximo = array[leftIndex];
		
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i] < minimo) {
				minimo = array[i];
			}
			if (array[i] > maximo) {
				maximo = array[i];
			}
		}
		
		int[] contagem = new int[Math.abs(maximo) + Math.abs(minimo) + 1];		
		for (int i = leftIndex; i <= rightIndex; i++) {		// contar frequencia
			contagem[array[i] + Math.abs(minimo)]++;
		}
		
		int soma = 0;
		for (int j = 0; j < contagem.length; j++) {			// calcular cumulativa
			soma += contagem[j];
			contagem[j] = soma;
		}
		
		Integer[] b = new Integer[soma];	
		for (int k = soma - 1; k >= 0; k--) {				// percorrer array de contagem, ordenando em b
			
				b[contagem[array[k] + Math.abs(minimo)] - 1] = array[k];
				contagem[array[k] + Math.abs(minimo)] -= 1;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = b[i];
		}
	}
}
