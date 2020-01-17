package roteiro;

import java.util.Arrays;
import java.util.Random;
import roteiro.FluxoDeDados;

public class ExecuteSorting<T extends Comparable<T>> {
	
	public static void main(String[] args) {
		
		FluxoDeDados fd = new FluxoDeDados();
		fd.salvar("alg time sample");
		Random r = new Random();
		Integer[] aleatorio;
		int base = 20000;
		
		Sorting<Integer> selection = new Selection<>();
		Sorting<Integer> insertion = new InsertionSort<>();
		Sorting<Integer> quick = new QuickSort<>();
		Sorting<Integer> merge = new MergeSort<>();	
		Sorting[] algs = {selection, insertion, quick, merge};
			
		while (base <= 40000) {
			
			aleatorio = new Integer[base];
			
			for (int i = 0; i < base; i++) {
				aleatorio[i] = r.nextInt(base);
			}
			Integer[] copia = Arrays.copyOf(aleatorio, base);
			
			for (Sorting algoritmo : algs) {
				algoritmo.sort(copia);
				fd.salvar(algoritmo.getNome() + algoritmo.tempoDeExecucao() + " " + base);
				copia = aleatorio;
			}
			base += 1000;
		}
		
	}

}
