package roteiro;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements Sorting<T> {

	private long tempoFinal;
	private long tempoInicial;
	private String nome = "merge ";

	@Override
	public void sort(T[] array) {
		
		this.tempoInicial = System.nanoTime();
		
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		sorting(array, leftIndex, rightIndex);
		
		this.tempoFinal = System.nanoTime() - tempoInicial;
	}
	
	public void sorting(T[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < rightIndex) {
		
			int meio = (rightIndex + leftIndex) / 2;
			sorting(array, leftIndex, meio);
			sorting(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		} 
	}
	
	public void merge(T[] array, int leftIndex, int meio, int rightIndex) {
		T[] lista = Arrays.copyOf(array, rightIndex + 1);
		int i = leftIndex;
		int j = meio + 1;
		
		int cont = leftIndex;
		while (i <= meio && j <= rightIndex) {
			
			if (lista[i].compareTo(lista[j]) < 0) { // lista[i] eh menor
				array[cont] = lista[i];
				i++;
				
			} else { 
				array[cont] = lista[j]; // lista[j] eh menor ou igual
				j++;
			}
			cont++;
		}
		
		while(i <= meio) {
			array[cont] = lista[i];
			i++;
			cont++;
		}
		
		while(j <= rightIndex) {
			array[cont] = lista[j];
			j++;
			cont++;
		}
	}

	@Override
	public long tempoDeExecucao() {
		return this.tempoFinal;
	}

	@Override
	public String getNome() {
		return this.nome ;
	}

}
