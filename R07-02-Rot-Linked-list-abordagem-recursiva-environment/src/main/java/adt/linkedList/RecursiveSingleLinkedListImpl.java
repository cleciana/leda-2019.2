package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		return size(this); 
	}

	private int size(RecursiveSingleLinkedListImpl<T> recursiveSLL) {
		int size = 0;
		
		if (recursiveSLL != null) {
			
			if (recursiveSLL.data != null)
				size += 1;
			else
				return size;
		}
		return size += this.size(recursiveSLL.getNext());
	}


	@Override
	public T search(T element) {
		return search(this, element);
	}

	private T search(RecursiveSingleLinkedListImpl<T> recursiveSLL, T element) {
		if (recursiveSLL.getData() == null) 
			return null;
		
		if (recursiveSLL.getData().equals(element))
			return recursiveSLL.getData();
		
		return search(recursiveSLL.getNext(), element);
	}

	@Override
	public void insert(T element) {
		this.insert(this, element);
	}
	
	private void insert(RecursiveSingleLinkedListImpl<T> next2, T element) {
		
		if (next2.isEmpty()) {
			next2.setData(element);
			next2.setNext(new RecursiveSingleLinkedListImpl<>());
		
		} else {
			insert(next2.getNext(), element);
		}
	}

	@Override
	public void remove(T element) {
		remove(this, element);
	}

	private void remove(RecursiveSingleLinkedListImpl<T> recursiveSLL, T element) {
		
		if (recursiveSLL.getData() == null)
			return;
		
		if (recursiveSLL.getNext().getData().equals(element)) 
			recursiveSLL.setNext(recursiveSLL.getNext().getNext());
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size()];
		return toArray(array, 0, this);
		
//		RecursiveSingleLinkedListImpl<T> aux = this;
//		
//		int i = 0;
//		while(aux.data != null) {
//			array[i] = aux.data;
//			aux = aux.next;
//			i += 1;
//		}
//		return array;
	}

	private T[] toArray(T[] array, int i, RecursiveSingleLinkedListImpl<T> aux) {
		if (aux.data != null)
			array[i] = aux.data;
		
		return toArray(array, i+1, aux.next);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
