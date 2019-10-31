package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
@SuppressWarnings("unchecked")
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar
	 * apenas o comparator e mandar reordenar a heap usando esse comparator. Assim
	 * os metodos da heap não precisam saber se vai funcionar como max-heap ou
	 * min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int maximo = max(position, left(position), right(position));

		if (maximo == position)
			return;

		Util.swap(heap, position, maximo);
		heapify(maximo);
	}

	private int max(int i, int left, int right) {
		if (comparator.compare(heap[i], heap[left]) > 0 && comparator.compare(heap[i], heap[right]) > 0) {
			return i;

		} else if (comparator.compare(heap[i], heap[left]) < 0 && comparator.compare(heap[right], heap[left]) < 0) {
			return left;
		}
		return right;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		// TODO Implemente a insercao na heap aqui.
		this.index += 1;
		this.heap[index] = element;

		int i = index;
		while (i > 0 && comparator.compare(heap[i], heap[parent(i)]) > 0) {
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (index != -1) {
			this.heap = (T[]) new Comparable[this.heap.length];
		}

		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
	}

	@Override
	public T extractRootElement() {
		if (this.index == -1)
			return null;
		
		T auxReturn = heap[0];
		heap[0] = heap[index];
		heapify(0);

		return auxReturn;
	}

	@Override
	public T rootElement() {
		if (this.index == -1)
			return null;

		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		if (index != -1) {
			heap = (T[]) new Comparable[array.length];
			index = -1;
		}
		index += 1;
		for (int i = 0; i < array.length; i++) {
			heap[index] = array[i];
		}

		int i = parent(index);
		while (i > 0 && heap[i].compareTo(heap[parent(i)]) > 0) {
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
		return heap;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
