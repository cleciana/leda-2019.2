package roteiro;

public class QuickSort<T extends Comparable<T>> implements Sorting<T> {

	private long tempoInicial;
	private long tempoFinal;
	private String nome = "quick ";
	
	@Override
	public void sort(T[] array) {
		
		this.tempoInicial = System.nanoTime();
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		sorting(array, leftIndex, rightIndex);
		this.tempoFinal = System.nanoTime();
	}
	
	public void sorting(T[] array, int leftIndex, int rightIndex) {

		if (array.length > 0 && array.length != 1) {
			int esq = leftIndex;
			int dir = rightIndex;
			T pivo = array[(esq + dir) / 2];
			T troca;
			while (esq <= dir) {
				while (array[esq].compareTo(pivo) < 0) {
					esq = esq + 1;
				}
				while (array[dir].compareTo(pivo) > 0) {
					dir = dir - 1;
				}
				if (esq <= dir) {
					troca = array[esq];
					array[esq] = array[dir];
					array[dir] = troca;
					esq = esq + 1;
					dir = dir - 1;
				}
			}
			if (dir > leftIndex)
				sorting(array, leftIndex, dir);
			if (esq < rightIndex)
				sorting(array, esq, rightIndex);
		}
	}

	@Override
	public long tempoDeExecucao() {
		return this.tempoFinal - this.tempoInicial;
	}

	@Override
	public String getNome() {
		return this.nome ;
	}

}
