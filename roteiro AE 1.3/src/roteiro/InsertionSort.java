package roteiro;

public class InsertionSort<T extends Comparable<T>> implements Sorting<T> {

	private long tempoFinal;
	private long tempoInicial;
	private String nome = "insertion ";

	@Override
	public void sort(T[] array) {
		this.tempoInicial = System.nanoTime();
		
		for (int j = 1; j < array.length; j++) {

			T key = array[j];
			int i = j - 1;

			while (i >= 0 && array[i].compareTo(key) > 0) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
		this.tempoFinal = System.nanoTime() - tempoInicial;
	}
	
	@Override
	public long tempoDeExecucao() {
		return this.tempoFinal;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

}
