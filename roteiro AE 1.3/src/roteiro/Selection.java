package roteiro;

public class Selection <T extends Comparable<T>> implements Sorting<T> {

	private long tempoInicial;
	private long tempoFinal;
	private String nome = "selection ";
	
	@Override
	public void sort(T[] array) {
		this.tempoInicial = System.nanoTime();
		
		int indice = 0;
		for (int i = 0; i < array.length - 1; i++) {
			
			T menor = array[i];
			for (int j = i+1; j <= array.length - 1; j++) {
				
				if (array[j].compareTo(menor) < 0) {
					menor = array[j];
					indice = j;
				}
			}
			T auxiliar = array[i];
			array[i] = menor;
			array[indice] = auxiliar;
		}
		this.tempoFinal = System.nanoTime();
	}
	
	@Override
	public long tempoDeExecucao() {
		return this.tempoFinal - this.tempoInicial;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

}
