package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		if (this.isEmpty()) return 0;
		
		SingleLinkedListNode<T> node = this.getHead();
		int soma = 0;

		while (!node.isNIL()) {
			soma += 1;
			node = node.getNext();
		}
		return soma;
	}

	@Override
	public T search(T element) {
		if (!this.isEmpty()) {
			
			SingleLinkedListNode<T> node = this.getHead();

			while (!node.isNIL()) {
				if (node.getData().equals(element)) {
					return node.getData();
				}
				node = node.getNext();
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			SingleLinkedListNode<T> node = this.getHead();
			while (!node.isNIL()) {
				node = node.getNext();
			}
			node.setData(element);
			node.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			
			SingleLinkedListNode<T> node = this.getHead();

			while (!node.isNIL()) {
				
				if (node.getNext().getData().equals(element)) {
					node.setNext(node.getNext().getNext());
					return;
				} 
				node = node.getNext();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		
		SingleLinkedListNode<T> node = this.getHead();
		int size = this.size();
		T[] array = (T[]) new Comparable[size];
		int i = 0;
		
		while(!node.isNIL() && i < size) {
			array[i] = node.getData();
			node = node.getNext();
			i += 1;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
