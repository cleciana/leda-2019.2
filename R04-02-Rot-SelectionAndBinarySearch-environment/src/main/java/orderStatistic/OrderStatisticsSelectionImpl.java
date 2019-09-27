package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		
		if (array.length < k) return null;
		
		if (array.length == k)  {
			T maiorElemento = array[0];
			
			for (int i = 0; i < array.length; i++) {
				if (array[i].compareTo(maiorElemento) > 0) {
					maiorElemento = array[i];
				}
			}
			return maiorElemento;
			
		} else {
			T menorElemento = array[0];
			
			for (int i = 0; i < array.length; i++) {
				if (array[i].compareTo(menorElemento) < 0) {
					menorElemento = array[i];
				}
			}
			return estatisticaDeOrdem(array, k-1, menorElemento);
		}
	}

	private T estatisticaDeOrdem(T[] array, int k, T menorElemento) {	
		if (k == 0) {
			return menorElemento;
		}
		
		T menorAtual = array[0];
		for (int i = 0; i < array.length; i++) {			
			
			if (array[i].compareTo(menorAtual) <= 0 && array[i].compareTo(menorElemento) > 0) {
				menorAtual = array[i];
			}
		}
		return estatisticaDeOrdem(array, k-1, menorAtual);
	}
}
